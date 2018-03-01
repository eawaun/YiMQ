package main.java.com.yimq.groupunicast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.consumer.ConsumeStatus;
import com.yimq.client.consumer.DefaultMQPushConsumer;
import com.yimq.client.consumer.MessageReceivedListener;
import com.yimq.client.exception.MQClientException;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

public class ConsumerA {
    public static void main(String[] args) throws InterruptedException, MQClientException, InvalidProtocolBufferException, RemotingConnectException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("defaultConsumerGroupA");

        pushConsumer.setNamesrvAddr("localhost:8888");

        pushConsumer.subscribe(Constant.DEFAULT_GROUP_UNICAST_TOPIC);

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
