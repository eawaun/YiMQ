package com.yimq.common.consumer;

public class SubscribeType {
    /**
     * 广播，即组间广播，组内广播，每个consumer都会消费一次
     */
    public static final int BROADCAST = 1;

    /**
     * 组间广播，组内单播
     */
    public static final int GROUP_UNICAST = 2;
}
