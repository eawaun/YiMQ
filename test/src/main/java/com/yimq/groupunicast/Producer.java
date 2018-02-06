package com.yimq.groupunicast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.exception.MQClientException;
import com.yimq.client.producer.DefaultMQProducer;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

public class Producer {
    public static void main(String[] args) throws InterruptedException, MQClientException, InvalidProtocolBufferException, RemotingConnectException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("localhost:8888");

        producer.start();

        Message msg = new Message(Constant.DEFAULT_GROUP_UNICAST_TOPIC, "hello".getBytes());

        producer.send(msg);
    }
}
