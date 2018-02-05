package com.yimq.common.protocol.route;

import com.yimq.common.ProtobufConver;
import com.yimq.common.topic.TopicConfig;
import com.yimq.common.topic.TopicConfigProto;

import java.util.List;
import java.util.stream.Collectors;

public class TopicRouteData implements ProtobufConver<TopicRouteDataProto.TopicRouteData> {
    private String topic;
    private TopicConfig topicConfig;
    private List<BrokerData> brokerDatas;

    public TopicRouteData() {
    }

    public TopicRouteData(String topic, TopicConfig topicConfig, List<BrokerData> brokerDatas) {
        this.topic = topic;
        this.topicConfig = topicConfig;
        this.brokerDatas = brokerDatas;
    }

    public static TopicRouteData fromProto(TopicRouteDataProto.TopicRouteData proto) {
        List<BrokerData> brokerDataList = proto.getBrokerDatasList().stream().map(BrokerData::fromProto)
            .collect(Collectors.toList());
        TopicConfig topicConfig = TopicConfig.fromProto(proto.getTopicConfig());
        return new TopicRouteData(proto.getTopic(), topicConfig, brokerDataList);
    }

    @Override
    public TopicRouteDataProto.TopicRouteData toProto() {
        List<BrokerDataProto.BrokerData> brokerDataProtoList =
            brokerDatas.stream().map(BrokerData::toProto).collect(Collectors.toList());
        TopicConfigProto.TopicConfig topicConfigProto = this.topicConfig.toProto();
        return TopicRouteDataProto.TopicRouteData.newBuilder().setTopic(this.topic)
            .setTopicConfig(topicConfigProto).addAllBrokerDatas(brokerDataProtoList)
            .build();
    }

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

    public TopicConfig getTopicConfig() {
        return topicConfig;
    }

    public void setTopicConfig(TopicConfig topicConfig) {
        this.topicConfig = topicConfig;
    }
}
