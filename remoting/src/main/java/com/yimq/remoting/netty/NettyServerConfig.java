package com.yimq.remoting.netty;

public class NettyServerConfig {
    private int listenPort = 8888;
    private int workerGroupThreads = 3;

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public int getWorkerGroupThreads() {
        return workerGroupThreads;
    }

    public void setWorkerGroupThreads(int workerGroupThreads) {
        this.workerGroupThreads = workerGroupThreads;
    }
}
