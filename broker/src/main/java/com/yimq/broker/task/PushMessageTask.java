package com.yimq.broker.task;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.SendMsgRequestHeaderProto.SendMsgRequestHeader;
import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.ResponseCode;

import java.util.List;

public class PushMessageTask implements Runnable {

    private List<ConsumerInfo> consumers;
    private RemotingServer remotingServer;
    private String topic;
    private Message message;

    private long timeoutMills = 3000;

    public PushMessageTask(List<ConsumerInfo> consumers, RemotingServer remotingServer, String topic, Message message) {
        this.consumers = consumers;
        this.remotingServer = remotingServer;
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {
        List<String> failConsumerAddrs = Lists.newArrayList();

        for (ConsumerInfo consumer : consumers) {
            SendMsgRequestHeader requestHeader = SendMsgRequestHeader.newBuilder().setTopic(this.topic).build();
            RemotingCommand request = RemotingCommandBuilder.newRequestBuilder(RequestCode.CONSUME_MESSAGE_DIRECTLY)
                .setCustomHeader(requestHeader.toByteString())
                .setBody(ByteString.copyFrom(message.getBody())).build();

            try {
                RemotingCommand response = this.remotingServer.invokeSync(consumer.getChannel(), request, timeoutMills);
                switch (response.getCode()) {
                    case ResponseCode.SUCCESS:
                        //
                        break;
                    default:
                        failConsumerAddrs.add(consumer.getAddress());
                }
            } catch (InterruptedException | RemotingSendRequestException | RemotingTimeoutException e) {
                failConsumerAddrs.add(consumer.getAddress());
                e.printStackTrace();
            }
        }

        if (failConsumerAddrs.size() == 0) {
            //消息投递完成，更改消息状态

        } else {
            //更新发送失败的消费者列表，及下次投递时间

        }


    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getTimeoutMills() {
        return timeoutMills;
    }

    public void setTimeoutMills(long timeoutMills) {
        this.timeoutMills = timeoutMills;
    }

    public RemotingServer getRemotingServer() {
        return remotingServer;
    }

    public void setRemotingServer(RemotingServer remotingServer) {
        this.remotingServer = remotingServer;
    }
}
