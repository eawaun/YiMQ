package com.yimq.client.producer;

import com.yimq.common.message.MessageQueue;
import com.yimq.common.protocol.route.TopicRouteData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicPublishInfo {
    private boolean orderTopic = false;
    private List<MessageQueue> messageQueues = new ArrayList<>();
    private TopicRouteData topicRouteData;
    private final Random random = new Random();
    private AtomicInteger selector = new AtomicInteger(random.nextInt());

    public MessageQueue selectOneMessageQueue(final String lastBrokerName) {
        if (lastBrokerName == null) {
            return selectOneMessageQueue();
        }
        for (int i = 0; i < messageQueues.size(); i++) {
            int pos = Math.abs(this.selector.getAndIncrement()) % messageQueues.size();
            MessageQueue messageQueue = this.messageQueues.get(pos);
            if (!lastBrokerName.equals(messageQueue.getBrokerName())) {
                return messageQueue;
            }
        }
        return selectOneMessageQueue();
    }

    public MessageQueue selectOneMessageQueue() {
        int index = this.selector.getAndIncrement();
        int pos = Math.abs(index) % this.messageQueues.size();
        return this.messageQueues.get(pos);
    }

}
