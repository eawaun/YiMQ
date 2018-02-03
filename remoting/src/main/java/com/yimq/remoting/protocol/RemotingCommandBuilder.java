package com.yimq.remoting.protocol;

import com.yimq.common.protocol.RemotingCommandType;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

import java.util.concurrent.atomic.AtomicLong;

public class RemotingCommandBuilder {
    private static AtomicLong requestIdGenerator = new AtomicLong(0);

    public static RemotingCommand.Builder newRequestBuilder(int code) {
        long requestId = requestIdGenerator.getAndIncrement();
        if (requestId < 0) {
            requestId = 0;
        }

        return RemotingCommand.newBuilder().setType(RemotingCommandType.REQUEST_COMMAND).
            setRequestId(requestId).setCode(code);
    }

    public static RemotingCommand.Builder newResponseBuilder(RemotingCommand request) {
        return RemotingCommand.newBuilder().setType(RemotingCommandType.RESPONSE_COMMAND).
            setRequestId(request.getRequestId());
    }
}
