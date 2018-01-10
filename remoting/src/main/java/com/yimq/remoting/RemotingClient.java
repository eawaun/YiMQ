package com.yimq.remoting;


import com.yimq.remoting.protocol.RemotingCommandProto;

public interface RemotingClient extends RemotingService {

    RemotingCommandProto.RemotingCommand invokeSync(final String addr, final RemotingCommandProto.RemotingCommand request
            , final long timeoutMillis);

}
