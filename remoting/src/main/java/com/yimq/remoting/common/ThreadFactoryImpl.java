package com.yimq.remoting.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadFactoryImpl implements ThreadFactory {
    private final AtomicLong threadIndex = new AtomicLong(1);
    private final String threadNameprefix;

    public ThreadFactoryImpl(String threadNameprefix) {
        this.threadNameprefix = threadNameprefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadNameprefix + this.threadIndex.getAndIncrement());
    }
}
