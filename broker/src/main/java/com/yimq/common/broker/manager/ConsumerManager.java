package com.yimq.common.broker.manager;

import com.google.common.collect.Lists;
import com.yimq.common.broker.BrokerController;
import com.yimq.common.broker.exception.MQBrokerException;
import com.yimq.common.consumer.ConsumerGroupInfo;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.consumer.SubscribeType;
import com.yimq.common.message.MessageQueue;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.common.topic.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerManager {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerManager.class);

    private BrokerController brokerController;

    private Map<String/* topic */, Map<String/* consumerGroup */, ConsumerGroupInfo>> topicConsumerGroupMap
        = new ConcurrentHashMap<>();

    /**
     * 队列与消费者列表之间的映射，当从队列当中取消息时，会发送到对应的消费者列表
     * //todo init
     */
    private Map<String/* topic */, Map<Integer/* queueId */, List<ConsumerInfo>>> topicQueueConsumersMap =
        new ConcurrentHashMap<>();
    private Lock lockAddTopic = new ReentrantLock();
    private Lock lockAddConsumer = new ReentrantLock();
    private Map<String/* topic */, List<MessageQueue>> messageQueueInTopic = new ConcurrentHashMap<>();

    public ConsumerManager(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    /**
     * 替换掉原来的
     */
    public void addTopic(TopicConfig topicConfig) {
        this.lockAddTopic.lock();
        try {
            Map<Integer, List<ConsumerInfo>> queueConsumersMap = new ConcurrentHashMap<>();
            for (int i = 0; i < topicConfig.getQueueNums(); i++) {
                queueConsumersMap.put(i, Lists.newArrayList());
            }
            this.topicQueueConsumersMap.put(topicConfig.getTopic(), queueConsumersMap);

            List<MessageQueue> queues = Lists.newArrayList();
            for (int i = 0; i < topicConfig.getQueueNums(); i++) {
                queues.add(new MessageQueue(i));
            }
            this.messageQueueInTopic.put(topicConfig.getTopic(), queues);
        } finally {
            this.lockAddTopic.unlock();
        }
    }

    public void addConsumer(String topic, ConsumerInfo newConsumer) throws MQBrokerException {
        this.lockAddConsumer.lock();
        try {
            if (!this.brokerController.getTopicManager().checkIfTopicExists(topic)) {
                throw new MQBrokerException(ResponseCode.TOPIC_NOT_EXIST, "topic is not exist");
            }

            Map<String, ConsumerGroupInfo> consumerGroupMap = this.topicConsumerGroupMap.get(topic);
            if (consumerGroupMap == null) {
                consumerGroupMap = new ConcurrentHashMap<>();
                topicConsumerGroupMap.put(topic, consumerGroupMap);
            }

            ConsumerGroupInfo consumerGroup = consumerGroupMap.get(newConsumer.getConsumerGroup());
            if (consumerGroup == null) {
                consumerGroup = new ConsumerGroupInfo(newConsumer.getConsumerGroup(), Lists.newArrayList());
                consumerGroupMap.put(newConsumer.getConsumerGroup(), consumerGroup);
            }
            consumerGroup.addConsumer(newConsumer);

            this.rebalanceRelation(topic);
        } finally {
            this.lockAddConsumer.unlock();
        }
    }

    /**
     * 重新平衡队列与消费者关联关系
     *
     * 根据消费类型不同，分配方法不同，包括：
     * 广播：1条队列包含所有的消费者
     * 组内单播：一条队列只分配到组内一个消费者。如果有两个组，那队列就与两个消费者（一组一个）关联
     *
     * 有两层循环，但在轻量级应用的场景下，实际上每个循环的次数都很少
     * 第一层是遍历topic内所有消费者组
     * 第二层是一个topic配置的消息队列数量
     */
    private void rebalanceRelation(String topic) {
        Map<String/* consumerGroup */, ConsumerGroupInfo> consumerGroupMap = this.topicConsumerGroupMap.get(topic);

        TopicConfig topicConfig = this.brokerController.getTopicManager().getTopicConfigMap().get(topic);
        List<MessageQueue> messageQueues = this.messageQueueInTopic.get(topic);

        Map<Integer/* queueId */, List<ConsumerInfo>> queueConsumersMap = this.topicQueueConsumersMap.get(topic);

        Collection<ConsumerGroupInfo> consumerGroups = consumerGroupMap.values();
        for (ConsumerGroupInfo consumerGroup : consumerGroups) {//遍历topic下的消费者组
            List<ConsumerInfo> consumersInGroup = consumerGroup.getConsumers();//消费者组内的消费者

            if (SubscribeType.BROADCAST == topicConfig.getSubscribeType()) {
                //一个队列分配给所有消费者
                messageQueues.forEach(messageQueue -> {
                    List<ConsumerInfo> consumers = queueConsumersMap.get(messageQueue.getQueueId());
                    consumers.addAll(consumersInGroup);//组里的所有消费者都与队列关联
                });
            } else if (SubscribeType.GROUP_UNICAST == topicConfig.getSubscribeType()) {
                //队列平均分配给消费者，一个队列只能与一个消费者关联
                int queueSizes = messageQueues.size();
                int consumerSizes = consumersInGroup.size();
                for (int i = 0; i < queueSizes; i++) {
                    MessageQueue messageQueue = messageQueues.get(i);
                    List<ConsumerInfo> consumers = queueConsumersMap.get(messageQueue.getQueueId());//与队列关联的消费者
                    consumers.add(consumersInGroup.get(i % consumerSizes));//将消费者组内的消费者 添加到与队列关联的消费者列表
                }
            }
        }
    }

    public List<ConsumerInfo> findConsumers(final String topic, final int queueId) {
        return this.topicQueueConsumersMap.get(topic).get(queueId);
    }
}
