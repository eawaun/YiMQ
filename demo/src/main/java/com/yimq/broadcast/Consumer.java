package main.java.com.yimq.broadcast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.consumer.ConsumeStatus;
import com.yimq.client.consumer.DefaultMQPushConsumer;
import com.yimq.client.consumer.MessageReceivedListener;
import com.yimq.client.exception.MQClientException;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

import java.util.Arrays;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException, InvalidProtocolBufferException, RemotingConnectException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("defaultConsumerGroup");

        pushConsumer.setNamesrvAddr("localhost:8888");

        pushConsumer.subscribe(Constant.DEFAULT_BROADCAST_TOPIC);

        pushConsumer.registerMessageListener(new MessageReceivedListener() {
            @Override
            public ConsumeStatus consumeMessage(Message message) {
                String topic = message.getTopic();
                String content = new String(message.getBody());
                System.out.println(topic + "  content: " + content);
                return ConsumeStatus.CONSUME_SUCCESS;
            }
        });

        pushConsumer.start();
    }
}
