package com.yimq.common.broker.task;

import com.google.protobuf.ByteString;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.SendMsgRequestHeaderProto.SendMsgRequestHeader;
import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.ResponseCode;

public class PushMessageTask implements Runnable {

    private ConsumerInfo consumer;
    private RemotingServer remotingServer;
    private String topic;
    private Message message;

    private long timeoutMills = 3000;

    public PushMessageTask(ConsumerInfo consumer, RemotingServer remotingServer, String topic, Message message) {
        this.consumer = consumer;
        this.remotingServer = remotingServer;
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {
        //todo 添加重试，成功时修改db
        SendMsgRequestHeader requestHeader = SendMsgRequestHeader.newBuilder().setTopic(this.topic).build();
        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder(RequestCode.CONSUME_MESSAGE_DIRECTLY)
            .setCustomHeader(requestHeader.toByteString())
            .setBody(ByteString.copyFrom(message.getBody())).build();

        try {
            RemotingCommand response = this.remotingServer.invokeSync(consumer.getChannel(), request, timeoutMills);
            switch (response.getCode()) {
                case ResponseCode.SUCCESS:
                    //
                    break;
                default:
                    //
            }
        } catch (InterruptedException | RemotingSendRequestException | RemotingTimeoutException e) {
            //
            e.printStackTrace();
        }
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getTimeoutMills() {
        return timeoutMills;
    }

    public void setTimeoutMills(long timeoutMills) {
        this.timeoutMills = timeoutMills;
    }

    public ConsumerInfo getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerInfo consumer) {
        this.consumer = consumer;
    }

    public RemotingServer getRemotingServer() {
        return remotingServer;
    }

    public void setRemotingServer(RemotingServer remotingServer) {
        this.remotingServer = remotingServer;
    }
}
