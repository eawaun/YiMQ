package com.yimq.remoting.netty;

import com.yimq.remoting.InvokeCallback;
import com.yimq.remoting.SemaphoreReleaseOnlyOnce;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ResponseFuture {
    private final int requestId;
    private final long timeoutMillis;
    private InvokeCallback invokeCallback;
    private AtomicBoolean executeCallbackOnlyOnce = new AtomicBoolean(false);

    private final CountDownLatch waitResponseLatch = new CountDownLatch(1);

    private SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce;

    private volatile RemotingCommand responseCommand;
    private volatile boolean sendRequestOK = true;
    private volatile Throwable cause;

    /**
     * for sync request
     */
    public ResponseFuture(int requestId, long timeoutMillis) {
        this.requestId = requestId;
        this.timeoutMillis = timeoutMillis;
    }

    /**
     * for async request
     */
    public ResponseFuture(int requestId, long timeoutMillis, InvokeCallback invokeCallback,
        SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce) {
        this.requestId = requestId;
        this.timeoutMillis = timeoutMillis;
        this.invokeCallback = invokeCallback;
        this.semaphoreReleaseOnlyOnce = semaphoreReleaseOnlyOnce;
    }

    public void executeInvokeCallback() {
        if (invokeCallback != null) {
            if (this.executeCallbackOnlyOnce.compareAndSet(false, true)) {
                invokeCallback.operationComplete(this);
            }
        }
    }

    public void semaphoreRelease() {
        if (this.semaphoreReleaseOnlyOnce != null) {
            this.semaphoreReleaseOnlyOnce.release();
        }
    }

    public RemotingCommand waitResponse(final long timeoutMillis) throws InterruptedException {
        this.waitResponseLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        return this.responseCommand;
    }

    /**
     * wait response for a sync request rather than a async request
     */
    public void responseLatchCountDown() {
        this.waitResponseLatch.countDown();
    }

    public boolean isSendRequestOK() {
        return sendRequestOK;
    }

    public void setSendRequestOK(boolean sendRequestOK) {
        this.sendRequestOK = sendRequestOK;
    }

    public int getRequestId() {
        return requestId;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public RemotingCommand getResponseCommand() {
        return responseCommand;
    }

    public void setResponseCommand(RemotingCommand responseCommand) {
        this.responseCommand = responseCommand;
    }

    public InvokeCallback getInvokeCallback() {
        return invokeCallback;
    }
}
