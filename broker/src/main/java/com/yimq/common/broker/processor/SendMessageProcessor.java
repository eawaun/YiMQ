package com.yimq.common.broker.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.common.protocol.RequestCode;

public class SendMessageProcessor implements NettyRequestProcessor {
    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        switch (request.getCode()) {
            case RequestCode.SEND_MESSAGE:
                return this.sendMessage(ctx, request);
        }

        return null;
    }

    private RemotingCommand sendMessage(ChannelHandlerContext ctx, RemotingCommand request) {

        return null;
    }
}
