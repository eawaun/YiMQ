package com.yimq.common.util;

public class TimeUtil {
    public static int getTimestampInt() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
