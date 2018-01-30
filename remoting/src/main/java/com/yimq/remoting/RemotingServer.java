package com.yimq.remoting;

import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandProto;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutorService;

public interface RemotingServer extends RemotingService {

    RemotingCommandProto.RemotingCommand invokeSync(final Channel channel, final RemotingCommandProto.RemotingCommand request
        , final long timeoutMills) throws InterruptedException, RemotingSendRequestException, RemotingTimeoutException;

}
