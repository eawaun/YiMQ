package com.yimq.remoting.protocol;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.yimq.common.protocol.RemotingCommandType;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

public class RemotingCommandBuilder {
    private static AtomicLong requestIdGenerator = new AtomicLong(0);

    public static RemotingCommand.Builder newRequestBuilder(int code) {
        return RemotingCommand.newBuilder().setType(RemotingCommandType.REQUEST_COMMAND).
            setRequestId(requestIdGenerator.getAndIncrement()).setCode(code);
    }

    public static RemotingCommand.Builder newResponseBuilder(RemotingCommand request) {
        return RemotingCommand.newBuilder().setType(RemotingCommandType.RESPONSE_COMMAND).
            setRequestId(request.getRequestId());
    }
}
