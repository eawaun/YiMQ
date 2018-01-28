package com.yimq.common.topic;

import com.yimq.common.ProtobufConver;

public class TopicConfig implements ProtobufConver<TopicConfigProto.TopicConfig> {

    public static int defaultReadQueueNums = 16;
    public static int defaultWriteQueueNums = 16;

    private String topic;
    private int readQueueNums = defaultReadQueueNums;
    private int writeQueueNums = defaultWriteQueueNums;
    private int permission;

    public TopicConfig(String topic, int readQueueNums, int writeQueueNums, int permission) {
        this.topic = topic;
        this.readQueueNums = readQueueNums;
        this.writeQueueNums = writeQueueNums;
        this.permission = permission;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getReadQueueNums() {
        return readQueueNums;
    }

    public void setReadQueueNums(int readQueueNums) {
        this.readQueueNums = readQueueNums;
    }

    public int getWriteQueueNums() {
        return writeQueueNums;
    }

    public void setWriteQueueNums(int writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public static TopicConfig fromProto(TopicConfigProto.TopicConfig topicConfig) {
        return new TopicConfig(topicConfig.getTopic(), topicConfig.getReadQueueNums(), topicConfig.getWriteQueueNums()
            ,topicConfig.getPermission());
    }

    @Override
    public TopicConfigProto.TopicConfig toProto() {
        return TopicConfigProto.TopicConfig.newBuilder().setTopic(getTopic()).setReadQueueNums(getReadQueueNums())
            .setWriteQueueNums(getWriteQueueNums()).setPermission(getPermission()).build();
    }
}
