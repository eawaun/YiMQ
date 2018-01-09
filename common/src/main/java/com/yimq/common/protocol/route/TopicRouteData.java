package com.yimq.common.protocol.route;

import java.util.List;

public class TopicRouteData {
    private String topic;
    private List<BrokerData> brokerDatas;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<BrokerData> getBrokerDatas() {
        return brokerDatas;
    }

    public void setBrokerDatas(List<BrokerData> brokerDatas) {
        this.brokerDatas = brokerDatas;
    }


}
