package com.yimq.namesrv;

import com.yimq.remoting.netty.NettyServerConfig;

public class NamesrvStarter {
    public static void main(String[] args) {
        main0(args);
    }

    private static void main0(String[] args) {
        NettyServerConfig nettyServerConfig = new NettyServerConfig();

        NamesrvController controller = new NamesrvController(nettyServerConfig);
        controller.init();
        controller.start();
    }
}
