package com.yimq.common.broker;

import com.yimq.common.topic.TopicConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {

    private BrokerController brokerController;
    private Map<String/* topic */, TopicConfig> topicConfigMap = new ConcurrentHashMap<>();

    public TopicManager(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    public void addTopic(TopicConfig topicConfig) {
        getTopicConfigMap().put(topicConfig.getTopic(), topicConfig);
        this.brokerController.getMessageManager().addTopic(topicConfig);
    }

    public Map<String, TopicConfig> getTopicConfigMap() {
        return topicConfigMap;
    }

    public void setTopicConfigMap(Map<String, TopicConfig> topicConfigMap) {
        this.topicConfigMap = topicConfigMap;
    }
}
