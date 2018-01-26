package com.yimq.common.broker;

import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingServer;
import com.yimq.remoting.netty.NettyServerConfig;

public class BrokerController {
    private final BrokerConfig brokerConfig;
    private final NettyServerConfig nettyServerConfig;
    private final NettyClientConfig nettyClientConfig;

    private RemotingServer remotingServer;

    public BrokerController(BrokerConfig brokerConfig, NettyServerConfig nettyServerConfig, NettyClientConfig nettyClientConfig) {
        this.brokerConfig = brokerConfig;
        this.nettyServerConfig = nettyServerConfig;
        this.nettyClientConfig = nettyClientConfig;
    }

    public void init() {
        this.remotingServer = new NettyRemotingServer(this.nettyServerConfig);
    }


    public void start() {
        this.remotingServer.start();

        this.registerBroker();
    }

    public void registerBroker() {

    }
}
