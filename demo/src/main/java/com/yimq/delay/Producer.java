package com.yimq.delay;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.common.SendResult;
import com.yimq.client.exception.MQClientException;
import com.yimq.client.producer.DefaultMQProducer;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.common.util.TimeUtil;
import com.yimq.remoting.exception.RemotingConnectException;

public class Producer {
    public static void main(String[] args) throws InterruptedException, RemotingConnectException, InvalidProtocolBufferException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("localhost:8888");

        producer.start();

        long timeBegin = TimeUtil.getTimestampInt();

        Message msg = new Message(Constant.DEFAULT_BROADCAST_TOPIC, "hello".getBytes());
        msg.setDelayTime(TimeUtil.getTimestampInt() + 5);

        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
    }
}
