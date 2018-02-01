package com.yimq.client.producer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultMessageQueueSelector implements MessageQueueSelector {

    private Random random = new Random();
    private AtomicInteger queueSelector = new AtomicInteger(random.nextInt());

    @Override
    public int select(int queueNums, Object id) {
        int queueId = Math.abs(queueSelector.getAndIncrement() % queueNums);
        if (queueId < 0) {
            queueId = 0;
        }
        return queueId;
    }
}
