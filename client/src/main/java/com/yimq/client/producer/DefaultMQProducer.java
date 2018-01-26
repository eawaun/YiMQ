package com.yimq.client.producer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.ClientConfig;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto;
import com.yimq.common.protocol.route.BrokerDataProto;
import com.yimq.common.protocol.route.TopicRouteDataProto.TopicRouteData;
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

    private Map<String/* topic */, TopicRouteData> topicRouteDataMap = new ConcurrentHashMap<>();

    private final RemotingClient remotingClient;

    public DefaultMQProducer() {
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
        TopicRouteData topicRouteData = findTopicRouteData(msg.getTopic());
        BrokerDataProto.BrokerData brokerData = topicRouteData.getBrokerDatas(0);


        //2、与broker建立连接，发送数据

    }

    private TopicRouteData findTopicRouteData(String topic) throws InterruptedException, RemotingConnectException {



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
            TopicRouteDataMap topicRouteDataMap = TopicRouteDataMap.parseFrom(response.getBody());
            return topicRouteDataMap.getTopicRouteDataMapMap().get(topic);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        return null;
    }

}
