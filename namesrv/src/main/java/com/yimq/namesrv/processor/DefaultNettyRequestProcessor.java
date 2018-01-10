package com.yimq.namesrv.processor;

import com.yimq.common.protocol.RequestCode;
import com.yimq.remoting.netty.NettyRequestProcessor;
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
    public RemotingCommand.Builder process(ChannelHandlerContext ctx, RemotingCommand request) {
        switch (request.getCode()) {
            case RequestCode.GET_ALL_TOPIC_ROUTE_FROM_NAMESRV:
                return this.getAllTopicRouteFromNamesrv();
        }

        return null;
    }

    private RemotingCommand.Builder getAllTopicRouteFromNamesrv() {
        //todo topic route列表
        Map<String, TopicRouteData> topicRouteDataMap = new HashMap<>();

        BrokerData brokerData = BrokerData.newBuilder().setBrokerName("brokerName1")
            .putBrokerAddrs(1, "127.0.0.1:8899").build();
        List<BrokerData> brokerDatas = Arrays.asList(brokerData);

        TopicRouteData topicRouteData = TopicRouteData.newBuilder()
            .setTopic("topic1").addAllBrokerDatas(brokerDatas).build();

        TopicRouteDataMap topicRouteDataMapProto = TopicRouteDataMap.newBuilder()
            .putTopicRouteDataMap("topic1", topicRouteData).build();

        RemotingCommand.Builder responseBuilder = RemotingCommand.newBuilder()
            .setBody(topicRouteDataMapProto.toByteString());

        return responseBuilder;
    }
}
