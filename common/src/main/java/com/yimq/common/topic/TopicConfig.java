package com.yimq.common.topic;

import com.yimq.common.ProtobufConver;
import com.yimq.common.consumer.SubscribeType;

public class TopicConfig implements ProtobufConver<TopicConfigProto.TopicConfig> {

    public static int defaultQueueNums = 4;

    private String topic;
    private int queueNums = defaultQueueNums;
    private int subscribeType;

    public TopicConfig(String topic, int queueNums, int subscribeType) {
        this.topic = topic;
        this.queueNums = queueNums;
        this.subscribeType = subscribeType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQueueNums() {
        return queueNums;
    }

    public void setQueueNums(int queueNums) {
        this.queueNums = queueNums;
    }

    public int getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(int subscribeType) {
        this.subscribeType = subscribeType;
    }

    public static TopicConfig fromProto(TopicConfigProto.TopicConfig topicConfig) {
        return new TopicConfig(topicConfig.getTopic(), topicConfig.getQueueNums()
            ,topicConfig.getSubscribeType());
    }

    @Override
    public TopicConfigProto.TopicConfig toProto() {
        return TopicConfigProto.TopicConfig.newBuilder().setTopic(getTopic()).setQueueNums(getQueueNums())
            .setSubscribeType(getSubscribeType()).build();
    }
}
