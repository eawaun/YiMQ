package com.yimq.common.message;

public class Message {
    private String topic;
    private int queueId;
    private byte[] body;

    public Message(String topic, byte[] body) {
        this.topic = topic;
        this.body = body;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
