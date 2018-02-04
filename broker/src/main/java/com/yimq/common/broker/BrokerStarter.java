package com.yimq.common.broker;

import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyServerConfig;

public class BrokerStarter {
    public static void main(String[] args) throws InterruptedException {
        start(args);
    }

    private static void start(String[] args) throws InterruptedException {
        final BrokerConfig brokerConfig = new BrokerConfig();
        brokerConfig.setRegisterBrokerTimeoutMills(1111000);
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        nettyServerConfig.setListenPort(10911);
        final NettyClientConfig nettyClientConfig = new NettyClientConfig();

        BrokerController brokerController = new BrokerController(brokerConfig, nettyServerConfig, nettyClientConfig);

        brokerController.init();
        brokerController.start();
    }
}
