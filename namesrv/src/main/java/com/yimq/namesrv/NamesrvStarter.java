package com.yimq.namesrv;

import com.yimq.remoting.netty.NettyServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamesrvStarter {
    private static final Logger logger = LoggerFactory.getLogger(NamesrvStarter.class);

    public static void main(String[] args) {
        main0(args);
    }

    private static void main0(String[] args) {
        NettyServerConfig nettyServerConfig = new NettyServerConfig();
        NamesrvController controller = new NamesrvController(nettyServerConfig);

        controller.init();
        controller.start();

        logger.info("name server start success");
    }
}
