package com.yimq.common.protocol.route;

import com.yimq.common.ProtobufConver;

import java.util.List;
import java.util.stream.Collectors;

public class TopicRouteData implements ProtobufConver<TopicRouteDataProto.TopicRouteData> {
    private String topic;
    private List<BrokerData> brokerDatas;
    private List<QueueData> queueDatas;

    public TopicRouteData() {
    }

    public TopicRouteData(String topic, List<BrokerData> brokerDatas) {
        this.topic = topic;
        this.brokerDatas = brokerDatas;
    }

    public static TopicRouteData fromProto(TopicRouteDataProto.TopicRouteData proto) {
        List<BrokerData> brokerDataList = proto.getBrokerDatasList().stream().map(BrokerData::fromProto)
            .collect(Collectors.toList());
        return new TopicRouteData(proto.getTopic(), brokerDataList);
    }

    @Override
    public TopicRouteDataProto.TopicRouteData toProto() {
        List<BrokerDataProto.BrokerData> brokerDataProtoList =
            brokerDatas.stream().map(BrokerData::toProto).collect(Collectors.toList());

        return TopicRouteDataProto.TopicRouteData.newBuilder().setTopic(this.topic).addAllBrokerDatas(brokerDataProtoList)
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

    public List<QueueData> getQueueDatas() {
        return queueDatas;
    }

    public void setQueueDatas(List<QueueData> queueDatas) {
        this.queueDatas = queueDatas;
    }
}
