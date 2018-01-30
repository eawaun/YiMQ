package com.yimq.common.broker;

import com.yimq.common.broker.processor.BrokerProcessor;
import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.common.ThreadFactoryImpl;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingServer;
import com.yimq.remoting.netty.NettyServerConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrokerController {
    private final BrokerConfig brokerConfig;
    private final NettyServerConfig nettyServerConfig;
    private final NettyClientConfig nettyClientConfig;

    private ConsumerManager consumerManager;
    private MessageManager messageManager;

    private RemotingServer remotingServer;
    private ExecutorService remotingExecutor;

    public BrokerController(BrokerConfig brokerConfig, NettyServerConfig nettyServerConfig, NettyClientConfig nettyClientConfig) {
        this.brokerConfig = brokerConfig;
        this.nettyServerConfig = nettyServerConfig;
        this.nettyClientConfig = nettyClientConfig;
    }

    public void init() {
        this.consumerManager = new ConsumerManager();
        this.messageManager = new MessageManager(this);
        this.remotingExecutor = Executors.newFixedThreadPool(this.nettyServerConfig.getWorkerThreads(),
            new ThreadFactoryImpl("BrokerExecutorThread_"));
        this.remotingServer = new NettyRemotingServer(this.nettyServerConfig);
        this.remotingServer.registerProcessor(new BrokerProcessor(this), this.remotingExecutor);

    }

    public void start() throws InterruptedException {
        this.remotingServer.start();

        this.registerBroker();

        this.messageManager.dispatchMessage();
    }

    public void registerBroker() {

    }

    public RemotingServer getRemotingServer() {
        return remotingServer;
    }

    public ConsumerManager getConsumerManager() {
        return consumerManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
