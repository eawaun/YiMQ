package com.yimq.common.protocol.route;

import java.util.Map;

@Deprecated
public class BrokerData {
    private String cluster;
    private String brokerName;
    private Map<Integer/* brokerId */, String/* broker address */> brokerAddrs;
}
