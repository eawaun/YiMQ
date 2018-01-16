package com.yimq.client.producer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.route.TopicRouteDataProto.*;
import com.yimq.remoting.RemotingClient;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingClient;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.yimq.common.protocol.RequestCode.*;

public class DefaultMQProducer implements MQProducer {

    private Map<String/* topic */, TopicRouteData> topicRouteDataMap = new ConcurrentHashMap<>();

    private final RemotingClient remotingClient;

    public DefaultMQProducer() {
        this.remotingClient = new NettyRemotingClient(new NettyClientConfig());
    }

    @Override
    public void sendSync(Message msg) {
        //1、从namesrv得到topic路由信息


        //2、与broker建立连接，发送数据

    }

    private TopicRouteData findTopicRouteData(String topic) throws InterruptedException {
        TopicRouteData topicRouteData = topicRouteDataMap.get(topic);
        if (topicRouteData != null) {
            return topicRouteData;
        }
        //查询namesrv是否有该topic的路由信息
        RemotingCommand request = RemotingCommand.newBuilder().setCode(GET_ALL_TOPIC_ROUTE_FROM_NAMESRV).build();
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
