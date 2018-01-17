package com.yimq.remoting.protocol;

import java.util.concurrent.atomic.AtomicInteger;

public class RemotingCommandUtil {
    public static AtomicInteger requestIdGenerator = new AtomicInteger(0);
}
