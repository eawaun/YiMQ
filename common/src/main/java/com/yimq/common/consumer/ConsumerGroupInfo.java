package com.yimq.common.consumer;

import java.util.List;

public class ConsumerGroupInfo {
    private String groupName;
    private List<ConsumerInfo> consumers;

    public ConsumerGroupInfo() {
    }

    public ConsumerGroupInfo(String groupName, List<ConsumerInfo> consumers) {
        this.groupName = groupName;
        this.consumers = consumers;
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
