package com.yimq.common.consumer;

import io.netty.channel.Channel;

public class ConsumerInfo {
    private String consumerGroup;
    private String address;
    private String topic;
    private Channel channel;

    public ConsumerInfo() {
    }

    public ConsumerInfo(String consumerGroup, String address, String topic) {
        this.consumerGroup = consumerGroup;
        this.address = address;
        this.topic = topic;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsumerInfo that = (ConsumerInfo) o;

        if (getConsumerGroup() != null ? !getConsumerGroup().equals(that.getConsumerGroup()) : that.getConsumerGroup() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        return getTopic() != null ? getTopic().equals(that.getTopic()) : that.getTopic() == null;
    }

    @Override
    public int hashCode() {
        int result = getConsumerGroup() != null ? getConsumerGroup().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getTopic() != null ? getTopic().hashCode() : 0);
        return result;
    }
}
