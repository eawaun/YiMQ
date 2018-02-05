package com.yimq.remoting.netty;

public class NettyClientConfig {

    private int processRequestTaskThreads = 4;
    private int connectTimeoutMillis = 3600000;

    public int getProcessRequestTaskThreads() {
        return processRequestTaskThreads;
    }

    public void setProcessRequestTaskThreads(int processRequestTaskThreads) {
        this.processRequestTaskThreads = processRequestTaskThreads;
    }

    public int getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(int connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }
}
