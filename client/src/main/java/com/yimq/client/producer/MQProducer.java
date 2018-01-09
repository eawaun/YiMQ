package com.yimq.client.producer;

import com.yimq.client.MQAction;
import com.yimq.common.message.Message;

public interface MQProducer extends MQAction {
    void sendSync(Message msg);
}
