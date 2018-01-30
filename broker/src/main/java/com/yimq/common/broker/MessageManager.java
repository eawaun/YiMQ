package com.yimq.common.broker;

import com.yimq.common.broker.task.PushMessageTask;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.exception.MessageHandlerException;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.route.TopicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MessageManager {
    private final static Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private Map<String/* topic */, TopicInfo> topicInfoMap = new ConcurrentHashMap<>();

    private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private BrokerController brokerController;

    private ExecutorService dispatchMessageExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService pushMessageExecutor = Executors.newFixedThreadPool(4);

    public MessageManager(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    public void addMessage(String topic, Message message) throws MessageHandlerException {
        TopicInfo topicInfo = topicInfoMap.get(topic);
        if (topicInfo == null) {
            throw new MessageHandlerException("addMessage: topic[" + topic + "] doesn't exist!");
        }

        this.messageQueue.add(message);
    }

    public void saveMessage(String topic, Message message) {
        //todo db
    }

    public void dispatchMessage() throws InterruptedException {
        Runnable dispachTask = () -> {
            while (true) {
                Message message = null;
                try {
                    message = messageQueue.take();
                } catch (InterruptedException e) {
                    logger.error("dispatchMessage: InterruptedException", e);
                    break;
                }
                //获取消息的目标消费者
                TopicInfo topicInfo = topicInfoMap.get(message.getTopic());
                if (topicInfo == null) {
                    logger.warn("dispatchMessage: topic[{}] doesn't exist!", message.getTopic());
                    continue;
                }
                List<ConsumerInfo> consumers = this.brokerController.getConsumerManager().findConsumers(topicInfo);

                for (ConsumerInfo consumer : consumers) {
                    PushMessageTask task = new PushMessageTask(consumer, this.brokerController.getRemotingServer()
                        , message.getTopic(), message);
                    this.pushMessageExecutor.submit(task);
                }
            }
        };
        this.dispatchMessageExecutor.submit(dispachTask);
    }

}