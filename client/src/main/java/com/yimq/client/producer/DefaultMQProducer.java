package com.yimq.client.producer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.ClientConfig;
import com.yimq.client.ClientInstance;
import com.yimq.client.common.SendResult;
import com.yimq.client.exception.MQClientException;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.remoting.exception.RemotingConnectException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMQProducer extends ClientConfig implements MQProducer {

    private String producerGroup;

    private int sendMsgTimeoutMills = 3000;

    private int delayTime;

    private Map<String/* topic */, TopicRouteData> topicRouteDataMap = new ConcurrentHashMap<>();

    private ClientInstance clientInstance;

    public DefaultMQProducer() {
        this(Constant.DEFAULT_PRODUCER_GROUP);
    }

    public DefaultMQProducer(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    @Override
    public void start() {
        this.clientInstance = new ClientInstance(this.getNamesrvAddrList());
        this.clientInstance.start();
    }

    @Override
    public void shutdown() {
        this.clientInstance.shutdown();
    }

    /**
     * default send msg
     */
    @Override
    public SendResult send(Message msg) throws RemotingConnectException, InterruptedException, InvalidProtocolBufferException, MQClientException {
        return this.clientInstance.send(msg, this.getSendMsgTimeoutMills());
    }

    /**
     * send order msg
     */
    @Override
    public SendResult send(Message msg, MessageQueueSelector selector, int id) throws RemotingConnectException, InterruptedException, InvalidProtocolBufferException, MQClientException {
        return this.clientInstance.send(msg, selector, id, this.getSendMsgTimeoutMills());
    }


    public int getSendMsgTimeoutMills() {
        return sendMsgTimeoutMills;
    }

    public void setSendMsgTimeoutMills(int sendMsgTimeoutMills) {
        this.sendMsgTimeoutMills = sendMsgTimeoutMills;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
