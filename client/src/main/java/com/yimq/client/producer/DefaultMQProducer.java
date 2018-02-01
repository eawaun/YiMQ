package com.yimq.client.producer;

import com.google.protobuf.ByteString;
import com.yimq.client.ClientConfig;
import com.yimq.client.ClientInstance;
import com.yimq.client.common.SendResult;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.SendMsgRequestHeaderProto.SendMsgRequestHeader;
import com.yimq.common.protocol.route.BrokerData;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMQProducer extends ClientConfig implements MQProducer {

    private String producerGroup;

    private int sendMsgTimeoutMills = 3000;

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
        this.clientInstance = new ClientInstance(new NettyClientConfig());
        this.clientInstance.start();
    }

    @Override
    public void shutdown() {
        this.clientInstance.shutdown();
    }

    /**
     * default sync
     */
    @Override
    public SendResult send(Message msg) throws RemotingConnectException, InterruptedException {
        TopicRouteData topicRouteData = this.clientInstance.findTopicRouteDataFromNamesrv(msg.getTopic());

        BrokerData brokerData = this.clientInstance.chooseBroker(msg.getTopic());
        int queueId = this.clientInstance.chooseQueueId(topicRouteData.getTopicConfig().getQueueNums(), null);

        SendMsgRequestHeader sendMsgRequestHeader = SendMsgRequestHeader.newBuilder().setTopic(msg.getTopic())
            .setQueueId(queueId).build();

        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder()
            .setCode(RequestCode.SEND_MESSAGE)
            .setCustomHeader(sendMsgRequestHeader.toByteString())
            .setBody(ByteString.copyFrom(msg.getBody())).build();

        String brokerAddr = brokerData.getBrokerAddrs().get(Constant.MASTER_ID);
        return this.clientInstance.sendSync(brokerAddr, request, this.sendMsgTimeoutMills);
    }


    public int getSendMsgTimeoutMills() {
        return sendMsgTimeoutMills;
    }

    public void setSendMsgTimeoutMills(int sendMsgTimeoutMills) {
        this.sendMsgTimeoutMills = sendMsgTimeoutMills;
    }
}
