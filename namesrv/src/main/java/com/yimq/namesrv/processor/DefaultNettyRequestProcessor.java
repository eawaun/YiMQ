package com.yimq.namesrv.processor;

import com.yimq.common.protocol.RequestCode;
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
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) {
        switch (request.getCode()) {
            case RequestCode.GET_ALL_TOPIC_ROUTE_FROM_NAMESRV:
                return this.getAllTopicRouteFromNamesrv();
        }

        return null;
    }

    private RemotingCommand getAllTopicRouteFromNamesrv() {
        //todo topic route列表
        Map<String, TopicRouteData> topicRouteDataMap = new HashMap<>();

        BrokerData brokerData = BrokerData.newBuilder().setBrokerName("brokerName1")
            .putBrokerAddrs(1, "127.0.0.1:8881").build();
        BrokerData brokerData2 = BrokerData.newBuilder().setBrokerName("brokerName2")
            .putBrokerAddrs(1, "127.0.0.1:8882").build();

        List<BrokerData> brokerDatas = Arrays.asList(brokerData, brokerData2);

        TopicRouteData topicRouteData = TopicRouteData.newBuilder()
            .setTopic("topic1").addAllBrokerDatas(brokerDatas).build();

        TopicRouteDataMap topicRouteDataMapProto = TopicRouteDataMap.newBuilder()
            .putTopicRouteDataMap("topic1", topicRouteData).build();

        RemotingCommand response = RemotingCommandBuilder.newRequestBuilder()
            .setBody(topicRouteDataMapProto.toByteString()).build();

        return response;
    }
}
