package com.yimq.namesrv.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto.GetRouteInfoRequestHeader;
import com.yimq.common.protocol.header.RegisterBrokerRequestHeaderProto.RegisterBrokerRequestHeader;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.common.protocol.route.TopicRouteDataProto;
import com.yimq.common.topic.TopicConfigProto;
import com.yimq.common.topic.TopicConfigWrapper;
import com.yimq.namesrv.NamesrvController;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;


public class DefaultNettyRequestProcessor implements NettyRequestProcessor {

    private final NamesrvController namesrvController;

    public DefaultNettyRequestProcessor(NamesrvController namesrvController) {
        this.namesrvController = namesrvController;
    }

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
        return null;
    }

    private RemotingCommand getRouteInfoByTopic(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        GetRouteInfoRequestHeader requestHeader = GetRouteInfoRequestHeader.parseFrom(request.getCustomHeader());

        TopicRouteData topicRouteData = this.namesrvController.getRouteInfoManager().getTopicRouteDataByTopic(requestHeader.getTopic());

        TopicRouteDataProto.TopicRouteData topicRouteDataProto = topicRouteData.toProto();

        return RemotingCommandBuilder.newResponseBuilder(request).
            setBody(topicRouteDataProto.toByteString()).build();
    }

    private RemotingCommand registerBroker(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        RegisterBrokerRequestHeader registerBrokerRequestHeader = RegisterBrokerRequestHeader.parseFrom(request.getCustomHeader());

        TopicConfigProto.TopicConfigWrapper topicConfigWrapperProto = TopicConfigProto.TopicConfigWrapper.parseFrom(request.getBody());

        this.namesrvController.getRouteInfoManager().registerBroker(
            registerBrokerRequestHeader.getClusterName(),
            registerBrokerRequestHeader.getBrokerAddr(),
            registerBrokerRequestHeader.getBrokerName(),
            registerBrokerRequestHeader.getBrokerId(),
            TopicConfigWrapper.fromProto(topicConfigWrapperProto),
            ctx.channel()
        );

        return null;
    }

    private RemotingCommand unregisterBroker(ChannelHandlerContext ctx, RemotingCommand request) {
        return null;
    }
}
