package com.yimq.common.broker.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.broker.BrokerController;
import com.yimq.common.broker.exception.MQBrokerException;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.exception.MessageHandlerException;
import com.yimq.common.message.Message;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.common.protocol.header.RegisterConsumerRequestHeaderProto.RegisterConsumerRequestHeader;
import com.yimq.common.protocol.header.SendMsgRequestHeaderProto.SendMsgRequestHeader;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.common.protocol.RequestCode;

public class BrokerProcessor implements NettyRequestProcessor {

    private BrokerController brokerController;

    public BrokerProcessor(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException, MQBrokerException {
        switch (request.getCode()) {
            case RequestCode.SEND_MESSAGE:
                return this.processSendMessage(ctx, request);
            case RequestCode.REGISTER_CONSUMER:
                return this.registerConsumer(ctx, request);
        }
        return null;
    }

    private RemotingCommand processSendMessage(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        try {
            SendMsgRequestHeader requestHeader = SendMsgRequestHeader.parseFrom(request.getCustomHeader());
            Message message = new Message(requestHeader.getTopic(), request.getBody().toByteArray());
            //落地
            this.brokerController.getMessageManager().saveMessage(message.getTopic(), message);

            //todo 添加到消息队列中，如果是延迟任务，则不添加到队列中，而是放到数据库中
            this.brokerController.getMessageManager().addMessage(message.getTopic(), message);

            //返回成功
            RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request).setCode(ResponseCode.SUCCESS).build();
            return response;
        } catch (MessageHandlerException e) {
            e.printStackTrace();
        }
        return RemotingCommandBuilder.newResponseBuilder(request).setCode(ResponseCode.SYSTEM_ERROR).build();
    }

    private RemotingCommand registerConsumer(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException, MQBrokerException {
        RegisterConsumerRequestHeader requestHeader = RegisterConsumerRequestHeader.parseFrom(request.getCustomHeader());

        ConsumerInfo consumerInfo = new ConsumerInfo();
        consumerInfo.setTopic(requestHeader.getTopic());
        consumerInfo.setConsumerGroup(requestHeader.getConsumerGroup());
        consumerInfo.setAddress(ctx.channel().remoteAddress().toString());
        consumerInfo.setChannel(ctx.channel());

        this.brokerController.getConsumerManager().addConsumer(requestHeader.getTopic(), consumerInfo);

        RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request, ResponseCode.SUCCESS).build();
        return response;
    }
}
