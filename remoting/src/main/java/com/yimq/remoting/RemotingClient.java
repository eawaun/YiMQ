package com.yimq.remoting;

import com.yimq.remoting.protocol.RemotingCommand;

public interface RemotingClient extends RemotingService {

    RemotingCommand invokeSync(final String addr, final RemotingCommand request
            , final long timeoutMillis);

}
