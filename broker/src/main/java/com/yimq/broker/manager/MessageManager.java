package com.yimq.broker.manager;

import com.yimq.broker.BrokerController;
import com.yimq.broker.MessageStatus;
import com.yimq.broker.mapper.MessageMapper;
import com.yimq.broker.mapper.SqlSessionFactorySingleton;
import com.yimq.broker.model.MessagePO;
import com.yimq.broker.task.PushMessageTask;
import com.yimq.common.Constant;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.message.Message;
import com.yimq.common.util.IdGenerator;
import com.yimq.common.util.TimeUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MessageManager {
    private final static Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private BrokerController brokerController;

    private ExecutorService dispatchMessageExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService pushMessageExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public MessageManager(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    public boolean saveMessage(Message message) throws Exception {
        logger.info("saveMessage: message save in db, topic[{}], queueId[{}]", message.getTopic(), message.getQueueId());
        //获取消息投递到的消费者列表
        List<ConsumerInfo> consumers = this.brokerController.getConsumerManager().findConsumers(message.getTopic(), message.getQueueId());
        String consumersStr = consumers.stream().map(ConsumerInfo::getAddress).collect(Collectors.joining(",", "", ","));
        //存消息
        MessagePO messagePO = new MessagePO();
        messagePO.setId(IdGenerator.getInstance().generate());
        messagePO.setTopic(message.getTopic());
        messagePO.setQueueId(message.getQueueId());
        messagePO.setCreateTime(TimeUtil.getTimestampInt());
        messagePO.setUpdateTime(TimeUtil.getTimestampInt());
        messagePO.setDelayTime(message.getDelayTime());
        messagePO.setProducer(message.getProducer());
        messagePO.setConsumerList(consumersStr);
        messagePO.setRetryCount(Constant.RETRY_COUNT);
        messagePO.setContent(message.getBody());
        messagePO.setStatus(MessageStatus.NEW);

        try (SqlSession session = SqlSessionFactorySingleton.getInstance().openSession(true)) {
            MessageMapper mapper = session.getMapper(MessageMapper.class);
            int result = mapper.save(messagePO);
            return result > 0;
        }
    }

    public void dispatchMessage() throws InterruptedException {
        Runnable dispatchTask = () -> {
            SqlSession session = SqlSessionFactorySingleton.getInstance().openSession(true);
            try {
                MessageMapper mapper = session.getMapper(MessageMapper.class);
                List<Integer> statusList = Arrays.asList(MessageStatus.NEW, MessageStatus.WAIT);
                while (true) {
                    //从db从取出待消费的消息，包含新消息、延迟消息、上次未完成的消息
                    List<MessagePO> messagePOS = mapper.selectByStatusList(statusList);
                    //并发情况下，过滤掉更新状态到处理中但失败的消息（可能其它线程已更新过），采用乐观锁
                    messagePOS = messagePOS.stream().filter(messagePO ->
                        mapper.lockMessageToIng(messagePO.getId(), MessageStatus.ING, messagePO.getUpdateTime(), statusList) > 0)
                        .collect(Collectors.toList());

                    for (MessagePO messagePO : messagePOS) {
                        if (messagePO.getConsumerList() == null || Objects.equals(messagePO.getConsumerList(), "")) {
                            //无消费者需要投递，更改消息状态为成功
                            mapper.updateStatusByStatus(messagePO.getId(), MessageStatus.SUCCESS, MessageStatus.ING, messagePO.getUpdateTime());
                            continue;
                        }

                        String[] strs = messagePO.getConsumerList().split(",");
                        List<ConsumerInfo> consumers = Arrays.stream(strs)
                            .map(this.brokerController.getConsumerManager()::getConsumerInfoByAddress).filter(Objects::nonNull).collect(Collectors.toList());

                        PushMessageTask task = new PushMessageTask(consumers, this.brokerController.getRemotingServer()
                            , messagePO);
                        this.pushMessageExecutor.submit(task);
                    }

                    Thread.sleep(3000L);//todo
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        };
        this.dispatchMessageExecutor.submit(dispatchTask);
    }
}