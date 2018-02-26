package com.yimq.broker;

import com.yimq.broker.manager.MessageManager;
import com.yimq.broker.processor.BrokerProcessor;
import com.yimq.broker.client.BrokerClient;
import com.yimq.broker.manager.ConsumerManager;
import com.yimq.broker.manager.TopicManager;
import com.yimq.common.topic.TopicConfigWrapper;
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
    private TopicManager topicManager;
    private BrokerClient brokerClient;

    private RemotingServer remotingServer;
    private ExecutorService remotingExecutor;

    public BrokerController(BrokerConfig brokerConfig, NettyServerConfig nettyServerConfig, NettyClientConfig nettyClientConfig) throws InterruptedException {
        this.brokerConfig = brokerConfig;
        this.nettyServerConfig = nettyServerConfig;
        this.nettyClientConfig = nettyClientConfig;

        this.consumerManager = new ConsumerManager(this);
        this.messageManager = new MessageManager(this);
        this.topicManager = new TopicManager(this);
        this.remotingExecutor = Executors.newFixedThreadPool(this.nettyServerConfig.getWorkerThreads(),
            new ThreadFactoryImpl("BrokerExecutorThread_"));
        this.remotingServer = new NettyRemotingServer(this.nettyServerConfig);
        this.remotingServer.registerProcessor(new BrokerProcessor(this), this.remotingExecutor);
        this.brokerClient = new BrokerClient(this.nettyClientConfig);
    }

    public void init() {
        this.brokerClient.updateNamesrvAddrList(this.brokerConfig.getNamesrvAddr());
    }

    public void start() throws InterruptedException {
        this.remotingServer.start();

        this.brokerClient.start();
        this.registerBrokerToAllNamesrv();

        this.messageManager.dispatchMessage();
    }

    public void registerBrokerToAllNamesrv() {
        TopicConfigWrapper topicConfigWrapper = this.topicManager.buildTopicConfigWrapper();
        this.brokerClient.registerBrokerToAllNamesrv(
            this.brokerConfig.getBrokerClusterName(),
            this.getBrokerAddr(),
            this.brokerConfig.getBrokerName(),
            this.brokerConfig.getBrokerId(),
            topicConfigWrapper,
            this.brokerConfig.getRegisterBrokerTimeoutMills()
        );
    }

    public String getBrokerAddr() {
        return this.brokerConfig.getBrokerIP() + ":" + this.nettyServerConfig.getListenPort();
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

    public TopicManager getTopicManager() {
        return topicManager;
    }
}
