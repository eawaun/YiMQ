package com.yimq.common.topic;

import com.yimq.common.ProtobufConver;
import com.yimq.common.topic.TopicConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BrokerTopicConfigWrapper implements ProtobufConver<BrokerTopicConfigWrapperProto.BrokerTopicConfigWrapper> {
    private Map<String/* topic */, TopicConfig> brokerTopicConfigMap = new ConcurrentHashMap<>();

    public BrokerTopicConfigWrapper(Map<String, TopicConfig> brokerTopicConfigMap) {
        this.brokerTopicConfigMap = brokerTopicConfigMap;
    }

    public static BrokerTopicConfigWrapper fromProto(BrokerTopicConfigWrapperProto.BrokerTopicConfigWrapper brokerTopicConfigWrapper) {

        Map<String, TopicConfigProto.TopicConfig> topicConfigMapProto = brokerTopicConfigWrapper.getBrokerTopicConfigMapMap();

        Map<String, TopicConfig> brokerTopicConfigMap = new ConcurrentHashMap<>(topicConfigMapProto.size());
        topicConfigMapProto.forEach((k, v) -> {
            brokerTopicConfigMap.put(k, TopicConfig.fromProto(v));
        });

        return new BrokerTopicConfigWrapper(brokerTopicConfigMap);
    }

    @Override
    public BrokerTopicConfigWrapperProto.BrokerTopicConfigWrapper toProto() {

        Map<String, TopicConfigProto.TopicConfig> topicConfigMapProto = new ConcurrentHashMap<>(this.brokerTopicConfigMap.size());
        this.brokerTopicConfigMap.forEach((k, v) -> {
            topicConfigMapProto.put(k, v.toProto());
        });

        return BrokerTopicConfigWrapperProto.BrokerTopicConfigWrapper.newBuilder()
            .putAllBrokerTopicConfigMap(topicConfigMapProto).build();
    }

    public Map<String, TopicConfig> getBrokerTopicConfigMap() {
        return brokerTopicConfigMap;
    }

    public void setBrokerTopicConfigMap(Map<String, TopicConfig> brokerTopicConfigMap) {
        this.brokerTopicConfigMap = brokerTopicConfigMap;
    }
}
