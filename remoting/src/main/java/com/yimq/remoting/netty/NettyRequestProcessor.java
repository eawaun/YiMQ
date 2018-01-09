package com.yimq.remoting.netty;

import com.yimq.remoting.protocol.RemotingCommandProto;
import io.netty.channel.ChannelHandlerContext;

import static com.yimq.remoting.protocol.RemotingCommandProto.*;

public interface NettyRequestProcessor {
    RemotingCommand.Builder process(ChannelHandlerContext ctx, RemotingCommand request);
}
