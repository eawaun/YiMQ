package com.yimq.common.protocol.route;

import java.util.Map;

public class BrokerData {
    private String cluster;
    private String brokerName;
    private Map<Integer, String> brokerAddrs;

    public BrokerData() {
    }

    public BrokerData(String cluster, String brokerName, Map<Integer, String> brokerAddrs) {
        this.cluster = cluster;
        this.brokerName = brokerName;
        this.brokerAddrs = brokerAddrs;
    }

    public BrokerDataProto.BrokerData generateProto() {
        return BrokerDataProto.BrokerData.newBuilder().setCluster(this.cluster).
            setBrokerName(this.brokerName).putAllBrokerAddrs(this.brokerAddrs).build();
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Map<Integer, String> getBrokerAddrs() {
        return brokerAddrs;
    }

    public void setBrokerAddrs(Map<Integer, String> brokerAddrs) {
        this.brokerAddrs = brokerAddrs;
    }
}
