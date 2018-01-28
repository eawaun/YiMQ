package com.yimq.common.topic;

public class TopicConfig {

    public static int defaultReadQueueNums = 16;
    public static int defaultWriteQueueNums = 16;

    private String topic;
    private int readQueueNums = defaultReadQueueNums;
    private int writeQueueNums = defaultWriteQueueNums;
    private int permission;

    public TopicConfig(String topic) {
        this.topic = topic;
    }

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
}
