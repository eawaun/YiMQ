package com.yimq.common.broker;

import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyServerConfig;

public class BrokerStarter {
    public static void main(String[] args) {
        start(args);
    }

    private static void start(String[] args) {
        final BrokerConfig brokerConfig = new BrokerConfig();
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        final NettyClientConfig nettyClientConfig = new NettyClientConfig();

        BrokerController brokerController = new BrokerController(brokerConfig, nettyServerConfig, nettyClientConfig);

        brokerController.init();
        brokerController.start();
    }
}
