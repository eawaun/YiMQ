package com.yimq.common.protocol.route;

import com.yimq.common.ProtobufConver;

public class QueueData implements ProtobufConver<QueueDataProto.QueueData> {
    private String brokerName;
    private Integer readQueueNums;
    private Integer writeQueueNums;
    private Integer permission;

    public QueueData() {
    }

    public QueueData(String brokerName, Integer readQueueNums, Integer writeQueueNums, Integer permission) {
        this.brokerName = brokerName;
        this.readQueueNums = readQueueNums;
        this.writeQueueNums = writeQueueNums;
        this.permission = permission;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Integer getReadQueueNums() {
        return readQueueNums;
    }

    public void setReadQueueNums(Integer readQueueNums) {
        this.readQueueNums = readQueueNums;
    }

    public Integer getWriteQueueNums() {
        return writeQueueNums;
    }

    public void setWriteQueueNums(Integer writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public static QueueData fromProto(QueueDataProto.QueueData proto) {
        return new QueueData(proto.getBrokerName(), proto.getReadQueueNums(), proto.getWriteQueueNums(), proto.getPermission());
    }

    @Override
    public QueueDataProto.QueueData toProto() {
        return QueueDataProto.QueueData.newBuilder().setBrokerName(this.brokerName).setReadQueueNums(this.readQueueNums).
            setWriteQueueNums(this.writeQueueNums).setPermission(this.permission).build();
    }
}
