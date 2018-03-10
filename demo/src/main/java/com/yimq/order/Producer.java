package com.yimq.order;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.exception.MQClientException;
import com.yimq.client.producer.DefaultMQProducer;
import com.yimq.client.producer.MessageQueueSelector;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

public class Producer {
    public static void main(String[] args) throws InterruptedException, MQClientException, InvalidProtocolBufferException, RemotingConnectException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("localhost:8888");

        producer.start();

        for (int i = 0; i < 100; i++) {
            int orderId = i / 10;

            Message msg = new Message(Constant.DEFAULT_ORDER_TOPIC, ("hello:" + i).getBytes());

            producer.send(msg, new MessageQueueSelector() {
                @Override
                public int select(int queueNums, Object id) {
                    int businessId = (int) id;
                    return businessId % queueNums;
                }
            }, orderId);
        }
    }
}
