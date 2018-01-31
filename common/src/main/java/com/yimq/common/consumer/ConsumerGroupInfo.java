package com.yimq.common.consumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerGroupInfo {
    private String groupName;
    private List<ConsumerInfo> consumers;

    private final Lock consumersLock = new ReentrantLock();

    private final Random random = new Random();
    private AtomicInteger consumerSelector = new AtomicInteger(random.nextInt());

    public ConsumerGroupInfo() {
    }

    public ConsumerGroupInfo(String groupName, List<ConsumerInfo> consumers) {
        this.groupName = groupName;
        this.consumers = consumers;
    }

    public void addConsumer(ConsumerInfo newConsumer) {
        consumersLock.lock();
        try {
            for (ConsumerInfo consumer : this.consumers) {
                if (newConsumer.equals(consumer)) {
                    //覆盖掉原来的
                    consumers.remove(consumer);
                    break;
                }
            }
            this.consumers.add(newConsumer);
        } finally {
            consumersLock.unlock();
        }
    }

    public ConsumerInfo selectOneConsumer() {
        int index = Math.abs(consumerSelector.getAndIncrement()) % this.consumers.size();
        return this.consumers.get(index);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ConsumerInfo> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumerInfo> consumers) {
        this.consumers = consumers;
    }
}
