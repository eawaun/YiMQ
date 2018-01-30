package com.yimq.common;

import com.yimq.client.consumer.ConsumeStatus;
import com.yimq.client.consumer.DefaultMQPushConsumer;
import com.yimq.client.consumer.MessageReceivedListener;
import com.yimq.common.message.Message;

public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("defaultConsumerGroup");

        pushConsumer.subscribe("TopicTest1");

        pushConsumer.registerMessageListener(new MessageReceivedListener() {
            @Override
            public ConsumeStatus consumeMessage(Message message) {
                String topic = message.getTopic();
                String content = message.getBody().toString();
                System.out.println(topic + "  content: " + content);
                return ConsumeStatus.CONSUME_SUCCESS;
            }
        });
    }
}
