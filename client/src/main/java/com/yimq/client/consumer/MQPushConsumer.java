package com.yimq.client.consumer;

import com.yimq.client.MQAction;

public interface MQPushConsumer extends MQAction {
    void subscribe(String topic);

    void registerMessageListener(MessageReceivedListener messageListener);
}
