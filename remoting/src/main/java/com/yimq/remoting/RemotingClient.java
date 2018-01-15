package com.yimq.remoting;


import com.yimq.remoting.protocol.RemotingCommandProto;

import java.util.List;

public interface RemotingClient extends RemotingService {

    RemotingCommandProto.RemotingCommand invokeSync(final String addr, final RemotingCommandProto.RemotingCommand request
            , final long timeoutMillis);

    void updateNamesrvAddrList(final List<String> addrs);


}
