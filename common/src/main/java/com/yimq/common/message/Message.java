package com.yimq.common.message;

import com.google.protobuf.ByteString;
import com.yimq.common.ProtobufConver;

public class Message implements ProtobufConver<MessageProto.Message> {
    private int queueId;
    private String producer;
    private int delayTime;
    private String topic;
    private byte[] body;

    public Message(String topic, byte[] body) {
        this.topic = topic;
        this.body = body;
    }

    public Message(int queueId, int delayTime, String topic, byte[] body) {
        this.queueId = queueId;
        this.delayTime = delayTime;
        this.topic = topic;
        this.body = body;
    }

    public Message() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public static Message fromProto(MessageProto.Message messageProto) {
        return new Message(messageProto.getQueueId(), messageProto.getDelayTime(), messageProto.getTopic()
            , messageProto.getBody().toByteArray());
    }

    @Override
    public MessageProto.Message toProto() {
        return MessageProto.Message.newBuilder().setQueueId(getQueueId()).setDelayTime(getDelayTime())
            .setTopic(getTopic()).setBody(ByteString.copyFrom(getBody())).build();
    }
}
