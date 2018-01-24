package com.yimq.namesrv;

import com.yimq.namesrv.processor.DefaultNettyRequestProcessor;
import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.common.ThreadFactoryImpl;
import com.yimq.remoting.netty.NettyRemotingServer;
import com.yimq.remoting.netty.NettyServerConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NamesrvController {

    private RemotingServer remotingServer;

    private final NettyServerConfig nettyServerConfig;

    private ExecutorService remotingExecutor;

    public NamesrvController(NettyServerConfig nettyServerConfig) {
        this.nettyServerConfig = nettyServerConfig;
    }

    public void init() {
        this.remotingServer = new NettyRemotingServer(this.nettyServerConfig);

        this.remotingExecutor = Executors.newFixedThreadPool(this.nettyServerConfig.getWorkerGroupThreads(),
            new ThreadFactoryImpl("RemotingExecutorThread_"));

        this.registerProcessor();
    }

    private void registerProcessor() {
        this.remotingServer.registerDefaultProcessor(new DefaultNettyRequestProcessor(), this.remotingExecutor);
    }

    public void start() {
        this.remotingServer.start();
    }
}
