package com.yimq.remoting.netty;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.remoting.protocol.RemotingCommandProto;
import io.netty.channel.ChannelHandlerContext;

import static com.yimq.remoting.protocol.RemotingCommandProto.*;

public interface NettyRequestProcessor {
    RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException;
}
