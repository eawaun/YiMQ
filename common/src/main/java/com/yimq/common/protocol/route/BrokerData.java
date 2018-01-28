package com.yimq.common.protocol.route;

import com.yimq.common.ProtobufConver;

import java.util.Map;

public class BrokerData implements ProtobufConver<BrokerDataProto.BrokerData> {
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

    public static BrokerData fromProto(BrokerDataProto.BrokerData brokerDataProto) {
        return new BrokerData(brokerDataProto.getCluster(), brokerDataProto.getBrokerName(), brokerDataProto.getBrokerAddrsMap());
    }

    @Override
    public BrokerDataProto.BrokerData toProto() {
        return BrokerDataProto.BrokerData.newBuilder().setCluster(this.cluster).
            setBrokerName(this.brokerName).putAllBrokerAddrs(this.brokerAddrs).build();
    }
}
