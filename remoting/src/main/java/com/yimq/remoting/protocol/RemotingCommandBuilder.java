package com.yimq.remoting.protocol;

import java.util.concurrent.atomic.AtomicInteger;

import com.yimq.common.protocol.RemotingCommandType;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

public class RemotingCommandBuilder {
    private static AtomicInteger requestIdGenerator = new AtomicInteger(0);

    public static RemotingCommand.Builder newRequestBuilder() {
        return RemotingCommand.newBuilder().setType(RemotingCommandType.REQUEST_COMMAND).
            setRequestId(requestIdGenerator.getAndIncrement());
    }

    public static RemotingCommand.Builder newResponseBuilder(RemotingCommand request) {
        return RemotingCommand.newBuilder().setType(RemotingCommandType.RESPONSE_COMMAND).
            setRequestId(request.getRequestId());
    }
}
