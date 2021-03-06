package com.yimq.common;

public class Constant {

    public static final String NAMESRV_ADDR_ENV = "NAMESRV_ADDR";
    public static final String NAMESRV_ADDR_PROPERTY = "yimq.namesrv.addr";

    public static final int MASTER_ID = 0;//zero is master, others are slave

    public static final String DEFAULT_PRODUCER_GROUP = "DEFAULT_PRODUCER_GROUP";

    public static final String DEFAULT_BROADCAST_TOPIC = "DefaultBroadcastTopic";

    public static final String DEFAULT_GROUP_UNICAST_TOPIC = "DefaultGroupUnicastTopic";

    public static final String DEFAULT_ORDER_TOPIC = "DefaultOrderTopic";

    public static final int RETRY_COUNT = 5;
}
