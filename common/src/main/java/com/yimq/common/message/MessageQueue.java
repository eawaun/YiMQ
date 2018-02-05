package com.yimq.common.message;

public class MessageQueue {
    private int queueId;

    public MessageQueue(int queueId) {
        this.queueId = queueId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
}
