package com.yimq.common.topic;

import com.yimq.common.ProtobufConver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicConfigWrapper implements ProtobufConver<TopicConfigProto.TopicConfigWrapper> {
    private Map<String/* topic */, TopicConfig> brokerTopicConfigMap = new ConcurrentHashMap<>();

    public TopicConfigWrapper(Map<String, TopicConfig> brokerTopicConfigMap) {
        this.brokerTopicConfigMap = brokerTopicConfigMap;
    }

    public static TopicConfigWrapper fromProto(TopicConfigProto.TopicConfigWrapper proto) {

        Map<String, TopicConfigProto.TopicConfig> topicConfigMapProto = proto.getTopicConfigMapMap();

        Map<String, TopicConfig> brokerTopicConfigMap = new ConcurrentHashMap<>(topicConfigMapProto.size());
        topicConfigMapProto.forEach((k, v) -> {
            brokerTopicConfigMap.put(k, TopicConfig.fromProto(v));
        });

        return new TopicConfigWrapper(brokerTopicConfigMap);
    }



    @Override
    public TopicConfigProto.TopicConfigWrapper toProto() {
        Map<String, TopicConfigProto.TopicConfig> topicConfigMapProto = new ConcurrentHashMap<>(this.brokerTopicConfigMap.size());
        this.brokerTopicConfigMap.forEach((topic, topicConfig) -> {
            topicConfigMapProto.put(topic, topicConfig.toProto());
        });

        return TopicConfigProto.TopicConfigWrapper.newBuilder()
            .putAllTopicConfigMap(topicConfigMapProto).build();
    }

    public Map<String, TopicConfig> getBrokerTopicConfigMap() {
        return brokerTopicConfigMap;
    }

    public void setBrokerTopicConfigMap(Map<String, TopicConfig> brokerTopicConfigMap) {
        this.brokerTopicConfigMap = brokerTopicConfigMap;
    }
}
