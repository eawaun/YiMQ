package com.yimq.namesrv.processor;

import com.yimq.common.protocol.ResponseCode;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.remoting.netty.NettyRequestProcessor;
import static com.yimq.remoting.protocol.RemotingCommandProto.*;
import com.yimq.remoting.protocol.RemotingCommandProto;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.common.protocol.RequestCode;

import java.util.HashMap;
import java.util.Map;

import static com.yimq.common.protocol.RequestCode.*;

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
        RemotingCommand.Builder responseBuilder = null;
        return responseBuilder;
    }
}
