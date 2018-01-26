package com.yimq.namesrv.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto.GetRouteInfoRequestHeader;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.route.BrokerDataProto.BrokerData;
import com.yimq.common.protocol.route.TopicRouteDataProto.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefaultNettyRequestProcessor implements NettyRequestProcessor {
    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        switch (request.getCode()) {
            case RequestCode.GET_ALL_TOPIC_ROUTE_FROM_NAMESRV:
                return this.getAllTopicRouteFromNamesrv(ctx, request);
            case RequestCode.GET_ROUTEINFO_BY_TOPIC:
                return this.getRouteInfoByTopic(ctx, request);
            case RequestCode.REGISTER_BROKER:
                return this.registerBroker(ctx, request);
            case RequestCode.UNREGISTER_BROKER:
                return this.unregisterBroker(ctx, request);
        }

        return null;
    }

    private RemotingCommand getAllTopicRouteFromNamesrv(ChannelHandlerContext ctx, RemotingCommand request) {
        //todo topic route列表
        Map<String, TopicRouteData> topicRouteDataMap = new HashMap<>();

        BrokerData brokerData = BrokerData.newBuilder().setBrokerName("brokerName1")
            .putBrokerAddrs(1, "127.0.0.1:8881").build();
        BrokerData brokerData2 = BrokerData.newBuilder().setBrokerName("brokerName2")
            .putBrokerAddrs(1, "127.0.0.1:8882").build();

        List<BrokerData> brokerDatas = Arrays.asList(brokerData, brokerData2);

        TopicRouteData topicRouteData = TopicRouteData.newBuilder()
            .setTopic("TopicTest1").addAllBrokerDatas(brokerDatas).build();

        TopicRouteDataMap topicRouteDataMapProto = TopicRouteDataMap.newBuilder()
            .putTopicRouteDataMap("TopicTest1", topicRouteData).build();

        RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request)
            .setBody(topicRouteDataMapProto.toByteString()).build();

        return response;
    }

    private RemotingCommand getRouteInfoByTopic(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        GetRouteInfoRequestHeader requestHeader = GetRouteInfoRequestHeader.parseFrom(request.getCustomHeader());
        String topic = requestHeader.getTopic();


        BrokerData brokerData = BrokerData.newBuilder().setBrokerName("brokerName1")
            .putBrokerAddrs(1, "127.0.0.1:8881").build();
        BrokerData brokerData2 = BrokerData.newBuilder().setBrokerName("brokerName2")
            .putBrokerAddrs(1, "127.0.0.1:8882").build();

        List<BrokerData> brokerDatas = Arrays.asList(brokerData, brokerData2);

        TopicRouteData topicRouteData = TopicRouteData.newBuilder()
            .setTopic("TopicTest1").addAllBrokerDatas(brokerDatas).build();

        RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request)
            .setBody(topicRouteData.toByteString()).build();
        return response;
    }

    private RemotingCommand registerBroker(ChannelHandlerContext ctx, RemotingCommand request) {
        return null;
    }

    private RemotingCommand unregisterBroker(ChannelHandlerContext ctx, RemotingCommand request) {
        return null;
    }
}
