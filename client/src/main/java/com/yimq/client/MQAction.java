package com.yimq.client;

import com.yimq.remoting.exception.RemotingConnectException;

public interface MQAction {
    void start() throws InterruptedException, RemotingConnectException;

    void shutdown();
}
