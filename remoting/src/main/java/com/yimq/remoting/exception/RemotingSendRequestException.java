package com.yimq.remoting.exception;

public class RemotingSendRequestException extends RemotingException {

    public RemotingSendRequestException(String addr) {
        this(addr, null);
    }

    public RemotingSendRequestException(String addr, Throwable cause) {
        super("send request to host[" + addr + "] failed", cause);
    }
}
