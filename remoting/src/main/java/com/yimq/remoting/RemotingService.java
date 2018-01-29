package com.yimq.remoting;

import com.yimq.remoting.netty.NettyRequestProcessor;

import java.util.concurrent.ExecutorService;

public interface RemotingService {
    void start();

    void shutdown();

    void registerProcessor(final NettyRequestProcessor processor, final ExecutorService executorService);
}
