package com.yimq.common.protocol.broker;

import com.yimq.common.topic.TopicConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BrokerTopicConfigWrapper {
    private Map<String/* topic */, TopicConfig> brokerTopicConfigMap = new ConcurrentHashMap<>();


    public Map<String, TopicConfig> getBrokerTopicConfigMap() {
        return brokerTopicConfigMap;
    }

    public void setBrokerTopicConfigMap(Map<String, TopicConfig> brokerTopicConfigMap) {
        this.brokerTopicConfigMap = brokerTopicConfigMap;
    }
}
