package com.yimq.common.consumer;

public class ConsumerSubscribeInfo {
    private String consumerGroup;
    private String address;
    private String topic;
    private int subscribeType;

    public ConsumerSubscribeInfo() {
    }

    public ConsumerSubscribeInfo(String consumerGroup, String address, String topic, int subscribeType) {
        this.consumerGroup = consumerGroup;
        this.address = address;
        this.topic = topic;
        this.subscribeType = subscribeType;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(int subscribeType) {
        this.subscribeType = subscribeType;
    }
}
