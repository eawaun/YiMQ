package com.yimq.client.producer;

import com.yimq.common.message.Message;
import com.yimq.common.message.MessageQueue;

import java.util.List;

public interface MessageQueueSelector {
    MessageQueue select(List<MessageQueue> queues, Object id);
}
