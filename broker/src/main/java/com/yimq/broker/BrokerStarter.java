package com.yimq.broker;

import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrokerStarter {
    private static final Logger logger = LoggerFactory.getLogger(BrokerStarter.class);

    public static void main(String[] args) throws InterruptedException {
        start(args);
    }

    private static void start(String[] args) throws InterruptedException {
        final BrokerConfig brokerConfig = new BrokerConfig();
        brokerConfig.setRegisterBrokerTimeoutMills(3000);
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        nettyServerConfig.setListenPort(10911);
        final NettyClientConfig nettyClientConfig = new NettyClientConfig();

        BrokerController brokerController = new BrokerController(brokerConfig, nettyServerConfig, nettyClientConfig);

        brokerController.init();
        brokerController.start();

        logger.info("broker start success");
    }
}
