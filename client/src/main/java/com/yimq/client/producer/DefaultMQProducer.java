package com.yimq.client.producer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.Client;
import com.yimq.client.ClientConfig;
import com.yimq.common.Constant;
import com.yimq.common.message.Message;
import com.yimq.common.message.MessageQueue;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto;
import com.yimq.common.protocol.route.BrokerDataProto;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.common.protocol.route.TopicRouteDataProto;
import com.yimq.common.protocol.route.TopicRouteDataProto.TopicRouteDataMap;
import com.yimq.remoting.RemotingClient;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingClient;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto.GetRouteInfoRequestHeader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.yimq.common.protocol.RequestCode.GET_ALL_TOPIC_ROUTE_FROM_NAMESRV;
import static com.yimq.common.protocol.RequestCode.GET_ROUTEINFO_BY_TOPIC;

public class DefaultMQProducer extends ClientConfig implements MQProducer {

    private String producerGroup;

    private Map<String/* topic */, TopicRouteData> topicRouteDataMap = new ConcurrentHashMap<>();

    private final RemotingClient remotingClient;

    private Client client;

    public DefaultMQProducer() {
        this(Constant.DEFAULT_PRODUCER_GROUP);
    }

    public DefaultMQProducer(String producerGroup) {
        this.producerGroup = producerGroup;
        this.remotingClient = new NettyRemotingClient(new NettyClientConfig());
    }

    @Override
    public void start() {
        this.remotingClient.updateNamesrvAddrList(this.getNamesrvAddrList());
        this.remotingClient.start();
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void sendSync(Message msg) throws RemotingConnectException, InterruptedException {
        //1、从namesrv得到topic路由信息
        TopicPublishInfo topicPublishInfo = findTopicPublishInfo(msg.getTopic());
        if (topicPublishInfo != null) {
            MessageQueue mqSelected = topicPublishInfo.selectOneMessageQueue();
            if (mqSelected != null) {

            }
        }

        //2、与broker建立连接，发送数据

    }

    private TopicPublishInfo findTopicPublishInfo(final String topic) {
        return null;
    }

    private TopicRouteData findTopicRouteDataFromNamesrv(String topic) throws InterruptedException, RemotingConnectException {


        TopicRouteData topicRouteData = topicRouteDataMap.get(topic);
        if (topicRouteData != null) {
            return topicRouteData;
        }
        //查询namesrv是否有该topic的路由信息
        GetRouteInfoRequestHeader requestHeader = GetRouteInfoRequestHeader.newBuilder().setTopic(topic).build();
        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder()
            .setCode(GET_ROUTEINFO_BY_TOPIC).setCustomHeader(requestHeader.toByteString()).build();
        RemotingCommand response =
            this.remotingClient.invokeSync(null, request, 3 * 1000);

        try {
            return TopicRouteData.fromProto(TopicRouteDataProto.TopicRouteData.parseFrom(response.getBody()));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void sendMessage(final MessageQueue messageQueue, final Message message, final TopicPublishInfo topicPublishInfo
        , final long timeoutMills) {
        String brokerAddr = this.client.findBrokerAddressInPublish(messageQueue.getBrokerName());
        if (brokerAddr == null) {
            this.findTopicPublishInfo(message.getTopic());
            brokerAddr = this.client.findBrokerAddressInPublish(messageQueue.getBrokerName());
        }

        if (brokerAddr != null) {
            /**
             * todo
             * send content:
             * brokerAddr
             * topic
             * producer group
             * queue id
             * body
             */
        }

    }

    private void sendMessageSync(final String brokerAddr, final String brokerName, final Message msg, final long timeoutMills
        , final RemotingCommand request) throws RemotingConnectException, InterruptedException {
        RemotingCommand response = this.remotingClient.invokeSync(brokerAddr, request, timeoutMills);

    }

}
