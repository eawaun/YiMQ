package com.yimq.common.broker.manager;

import com.yimq.common.broker.BrokerController;
import com.yimq.common.broker.task.PushMessageTask;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.exception.MessageHandlerException;
import com.yimq.common.message.Message;
import com.yimq.common.topic.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MessageManager {
    private final static Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private Map<String/* topic */, Map<Integer/* queueId */, LinkedBlockingQueue<Message>>> topicQueueMap =
        new ConcurrentHashMap<>();

    private BrokerController brokerController;

    private ExecutorService dispatchMessageExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService pushMessageExecutor = Executors.newFixedThreadPool(4);

    public MessageManager(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    public void addTopic(TopicConfig topicConfig) {
        Map<Integer, LinkedBlockingQueue<Message>> queueMap = this.topicQueueMap.get(topicConfig.getTopic());
        if (queueMap == null) {
            queueMap = new ConcurrentHashMap<>();
            for (int i = 0; i < topicConfig.getQueueNums(); i++) {
                queueMap.put(i, new LinkedBlockingQueue<>());
            }
            this.topicQueueMap.put(topicConfig.getTopic(), queueMap);
        }
    }


    public void addMessage(String topic, Message message) throws MessageHandlerException {
        TopicConfig topicConfig = this.brokerController.getTopicManager().getTopicConfigMap().get(topic);
        if (topicConfig == null) {
            throw new MessageHandlerException("addMessage: topic[" + topic + "] doesn't exist!");
        }

        Map<Integer, LinkedBlockingQueue<Message>> queueMap = topicQueueMap.get(topic);
        LinkedBlockingQueue<Message> queue = queueMap.get(message.getQueueId());

        queue.add(message);
    }

    public void saveMessage(String topic, Message message) {
        //todo db
        logger.info("saveMessage: message save in db, topic[{}], queueId[{}]", topic, message.getQueueId());
    }



    public void dispatchMessage() throws InterruptedException {
        Runnable dispatchTask = () -> {
            while (true) {
                for (Map<Integer/* queueId */, LinkedBlockingQueue<Message>> queueMap : topicQueueMap.values()) {
                    queueMap.forEach((queueId, queue) -> {
                        Message message = queue.poll();
                        if (message == null) {
                            return;
                        }

                        TopicConfig topicConfig = this.brokerController.getTopicManager().getTopicConfigMap().get(message.getTopic());
                        if (topicConfig == null) {
                            logger.warn("dispatchMessage: topic[{}] doesn't exist!", message.getTopic());
                            return;
                        }
                        //获取消息的目标消费者
                        List<ConsumerInfo> consumers = this.brokerController.getConsumerManager().findConsumers(topicConfig, queueId);

                        for (ConsumerInfo consumer : consumers) {
                            PushMessageTask task = new PushMessageTask(consumer, this.brokerController.getRemotingServer()
                                , message.getTopic(), message);
                            this.pushMessageExecutor.submit(task);
                        }
                    });
                }
            }
        };
        this.dispatchMessageExecutor.submit(dispatchTask);
    }

    public void handleFailOrUnHandleMessage() {
        //从db取消息，包括延迟发送的消息

        //添加到队列中
    }


}