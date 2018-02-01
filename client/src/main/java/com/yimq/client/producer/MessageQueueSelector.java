package com.yimq.client.producer;

public interface MessageQueueSelector {

    /**
     *
     * @param queueNums
     * @param id 发送顺序消息时，用来指定queue
     * @return
     */
    int select(int queueNums, Object id);
}
