package com.yimq.common.broker.manager;

import com.yimq.common.Constant;
import com.yimq.common.broker.BrokerController;
import com.yimq.common.consumer.SubscribeType;
import com.yimq.common.topic.TopicConfig;
import com.yimq.common.topic.TopicConfigWrapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {

    private BrokerController brokerController;
    private Map<String/* topic */, TopicConfig> topicConfigMap = new ConcurrentHashMap<>();

    public TopicManager(BrokerController brokerController) {
        this.brokerController = brokerController;

        String defalutBroadcastTopic = Constant.DEFAULT_BROADCAST_TOPIC;
        TopicConfig broadcastTopicConfig = new TopicConfig(defalutBroadcastTopic, 2, SubscribeType.BROADCAST);
        this.addTopic(broadcastTopicConfig);

        String defaultGroupUnicastTopic = Constant.DEFAULT_GROUP_UNICAST_TOPIC;
        TopicConfig groupUnicastTopic = new TopicConfig(defaultGroupUnicastTopic, 3, SubscribeType.GROUP_UNICAST);
        this.addTopic(groupUnicastTopic);
    }

    public void addTopic(TopicConfig topicConfig) {
        getTopicConfigMap().put(topicConfig.getTopic(), topicConfig);
        this.brokerController.getMessageManager().addTopic(topicConfig);
        this.brokerController.getConsumerManager().addTopic(topicConfig);
    }

    public boolean checkIfTopicExists(String topic) {
        return topicConfigMap.containsKey(topic);
    }

    public TopicConfigWrapper buildTopicConfigWrapper() {
        return new TopicConfigWrapper(this.topicConfigMap);
    }

    public Map<String, TopicConfig> getTopicConfigMap() {
        return topicConfigMap;
    }
}
