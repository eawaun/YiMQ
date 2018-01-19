package com.yimq.remoting.protocol;

import java.util.concurrent.atomic.AtomicInteger;

import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

public class RemotingCommandBuilder {
    private static AtomicInteger requestIdGenerator = new AtomicInteger(0);

    public static RemotingCommand.Builder newBuilder() {
        return RemotingCommand.newBuilder().setRequestId(requestIdGenerator.getAndIncrement());
    }
}
