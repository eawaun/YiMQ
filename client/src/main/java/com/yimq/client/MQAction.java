package com.yimq.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.exception.MQClientException;
import com.yimq.remoting.exception.RemotingConnectException;

public interface MQAction {
    void start() throws InterruptedException, RemotingConnectException, InvalidProtocolBufferException, MQClientException;

    void shutdown();
}
