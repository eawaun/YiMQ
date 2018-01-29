package com.yimq.common.broker.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.RequestCode;
import io.netty.channel.ChannelHandlerContext;

public class ConsumerRequestProcessor implements NettyRequestProcessor {
    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        switch (request.getCode()) {
            case RequestCode.REGISTER_CONSUMER:
                return this.registerConsumer(ctx, request);
        }

        return null;
    }

    private RemotingCommand registerConsumer(ChannelHandlerContext ctx, RemotingCommand request) {

        return null;
    }
}
