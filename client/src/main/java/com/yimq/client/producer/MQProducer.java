package com.yimq.client.producer;

import com.yimq.client.MQAction;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

public interface MQProducer extends MQAction {
    void start();

    void shutdown();

    void sendSync(Message msg) throws RemotingConnectException, InterruptedException;
}
