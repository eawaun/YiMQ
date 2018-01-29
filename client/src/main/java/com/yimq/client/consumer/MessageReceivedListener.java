package com.yimq.client.consumer;

import com.yimq.common.message.Message;

public interface MessageReceivedListener {
    ConsumeStatus consumeMessage(Message message);
}
