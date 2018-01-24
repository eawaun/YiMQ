package com.yimq.remoting;

import com.yimq.remoting.netty.NettyRequestProcessor;

import java.util.concurrent.ExecutorService;

public interface RemotingServer extends RemotingService {

    void registerDefaultProcessor(final NettyRequestProcessor processor, final ExecutorService executorService);
}
