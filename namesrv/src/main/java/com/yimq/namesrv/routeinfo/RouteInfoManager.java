package com.yimq.namesrv.routeinfo;

import com.yimq.common.protocol.route.BrokerDataProto.BrokerData;
import com.yimq.common.protocol.route.TopicRouteDataProto.TopicRouteData;

import java.util.Map;

public class RouteInfoManager {

    private Map<String/* brokerName */, BrokerData> brokerDataTable;

    public void registerBroker(final String clusterName, final String brokerAddr, final String brokerName) {

    }

    public TopicRouteData pickupTopicRouteData(final String topic) {

    }
}
