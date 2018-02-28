package com.yimq.broker.task;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.yimq.broker.MessageStatus;
import com.yimq.broker.mapper.MessageMapper;
import com.yimq.broker.mapper.SqlSessionFactorySingleton;
import com.yimq.broker.model.MessagePO;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.util.TimeUtil;
import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.ResponseCode;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.stream.Collectors;

public class PushMessageTask implements Runnable {

    private List<ConsumerInfo> consumers;
    private RemotingServer remotingServer;
    private MessagePO messagePO;

    private long timeoutMills = 3000;

    public PushMessageTask(List<ConsumerInfo> consumers, RemotingServer remotingServer, MessagePO messagePO) {
        this.consumers = consumers;
        this.remotingServer = remotingServer;
        this.messagePO = messagePO;
    }

    @Override
    public void run() {
        List<String> failConsumerAddrs = Lists.newArrayList();
        Message message = new Message();
        message.setQueueId(this.messagePO.getQueueId());
        message.setDelayTime(this.messagePO.getDelayTime());
        message.setProducer(this.messagePO.getProducer());
        message.setTopic(this.messagePO.getTopic());
        message.setBody(this.messagePO.getContent());

        for (ConsumerInfo consumer : consumers) {
            RemotingCommand request = RemotingCommandBuilder.newRequestBuilder(RequestCode.CONSUME_MESSAGE_DIRECTLY)
                .setBody(ByteString.copyFrom(message.toProto().toByteArray())).build();

            try {
                RemotingCommand response = this.remotingServer.invokeSync(consumer.getChannel(), request, timeoutMills);
                switch (response.getCode()) {
                    case ResponseCode.SUCCESS:
                        break;
                    default:
                        failConsumerAddrs.add(consumer.getAddress());
                }
            } catch (InterruptedException | RemotingSendRequestException | RemotingTimeoutException e) {
                failConsumerAddrs.add(consumer.getAddress());
                e.printStackTrace();
            }
        }

        try (SqlSession session = SqlSessionFactorySingleton.getInstance().openSession(true)) {
            MessageMapper mapper = session.getMapper(MessageMapper.class);
            if (failConsumerAddrs.size() == 0) {
                //消息投递完成，更改消息状态
                mapper.updateStatusByStatus(messagePO.getId(), MessageStatus.SUCCESS, MessageStatus.ING, messagePO.getUpdateTime());
            } else {
                if (messagePO.getRetryCount() == 0) {
                    mapper.updateStatusByStatus(messagePO.getId(), MessageStatus.FAIL, MessageStatus.ING, messagePO.getUpdateTime());
                } else {
                    //更新发送失败的消费者列表，及下次投递时间 todo 默认10秒后
                    String consumersStr = failConsumerAddrs.stream().collect(Collectors.joining(","));
                    mapper.updateFailConsumers(messagePO.getId(), MessageStatus.WAIT, MessageStatus.ING, consumersStr
                        , TimeUtil.getTimestampInt() + 10);
                }
            }
        }
    }

    public long getTimeoutMills() {
        return timeoutMills;
    }

    public void setTimeoutMills(long timeoutMills) {
        this.timeoutMills = timeoutMills;
    }
}
