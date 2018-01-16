package com.yimq.remoting.common;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class RemotingUtil {

    public static SocketAddress string2SocketAddress(final String addr) {
        String[] str = addr.split(":");
        return new InetSocketAddress(str[0], Integer.parseInt(str[1]));
    }
}
