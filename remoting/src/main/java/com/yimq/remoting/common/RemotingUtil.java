package com.yimq.remoting.common;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class RemotingUtil {

    public static SocketAddress string2SocketAddress(final String addr) {
        String[] str = addr.split(":");
        return new InetSocketAddress(str[0], Integer.parseInt(str[1]));
    }

    public static String channel2Addr(final Channel channel) {
        if (null == channel) {
            return "";
        }
        SocketAddress remote = channel.remoteAddress();
        final String addr = null != remote ? remote.toString() : "";
        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(index + 1);
            }
            return addr;
        }
        return "";
    }
}
