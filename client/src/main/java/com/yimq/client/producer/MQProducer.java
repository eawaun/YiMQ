package com.yimq.client.producer;

import com.yimq.client.MQAction;
import com.yimq.client.common.SendResult;
import com.yimq.common.message.Message;
import com.yimq.remoting.exception.RemotingConnectException;

public interface MQProducer extends MQAction {

    SendResult send(Message msg) throws RemotingConnectException, InterruptedException;
}
