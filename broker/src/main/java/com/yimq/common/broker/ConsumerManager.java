package com.yimq.common.broker;

import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.protocol.route.TopicInfo;
import com.yimq.common.consumer.SubscribeType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerManager {
    private Map<String/* topic */, Map<String/* consumerGroup */, List<ConsumerInfo>>> topicConsumerGroupMap
        = new ConcurrentHashMap<>();

    private final Random random = new Random();
    private AtomicInteger groupConcurrentlySelector = new AtomicInteger(random.nextInt());

    public void addConsumer(String topic, ConsumerInfo newConsumer) {
        Map<String, List<ConsumerInfo>> consumerGroupMap = this.topicConsumerGroupMap.get(topic);
        if (consumerGroupMap == null) {
            consumerGroupMap = new ConcurrentHashMap<>();
            topicConsumerGroupMap.put(topic, consumerGroupMap);
        }

        List<ConsumerInfo> consumers = consumerGroupMap.get(newConsumer.getConsumerGroup());
        for (ConsumerInfo consumer : consumers) {
            if (newConsumer.equals(consumer)) {
                //覆盖掉原来的
                consumers.remove(consumer);
                break;
            }
        }
        consumers.add(newConsumer);
    }

    public List<ConsumerInfo> findConsumers(TopicInfo topicInfo) {
        String topic = topicInfo.getTopic();
        switch (topicInfo.getSubscribeType()) {
            case SubscribeType.BROADCAST:
                return this.findConsumersForBroadcast(topic);
            case SubscribeType.GROUP_CONCURRENTLY:
                return this.findConsumersForGroupConcurrently(topic);
            case SubscribeType.GROUP_SERIAL:
                return this.findConsumersForGroupSerial(topic);
            default:
                return null;
        }

    }

    private List<ConsumerInfo> findConsumersForBroadcast(String topic) {
        Collection<List<ConsumerInfo>> consumersInGroups = topicConsumerGroupMap.get(topic).values();

        List<ConsumerInfo> targetConsumers = new ArrayList<>();
        for (List<ConsumerInfo> consumers : consumersInGroups) {
            targetConsumers.addAll(consumers);
        }
        return targetConsumers;
    }

    /**
     * 随机选择一个消费者
     * @param topic
     * @return
     */
    private List<ConsumerInfo> findConsumersForGroupConcurrently(String topic) {
        Collection<List<ConsumerInfo>> consumersInGroups = topicConsumerGroupMap.get(topic).values();

        List<ConsumerInfo> targetConsumers = new ArrayList<>();
        for (List<ConsumerInfo> consumers : consumersInGroups) {
            int index = Math.abs(this.groupConcurrentlySelector.getAndIncrement()) % consumers.size();
            targetConsumers.add(consumers.get(index));
        }
        return targetConsumers;
    }

    private List<ConsumerInfo> findConsumersForGroupSerial(String topic) {
        //todo
        return null;
    }


}
