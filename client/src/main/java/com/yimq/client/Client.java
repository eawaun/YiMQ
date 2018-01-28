package com.yimq.client;

import com.yimq.common.Constant;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
    private final Map<String/* brokerName */, Map<Integer/* brokerId */, String/* address */>> brokerAddrTable =
        new ConcurrentHashMap<>();

    public String findBrokerAddressInPublish(final String brokerName) {
        Map<Integer, String> map = this.brokerAddrTable.get(brokerName);
        if (!MapUtils.isEmpty(map)) {
            return map.get(Constant.MASTER_ID);
        }
        return null;
    }
}
