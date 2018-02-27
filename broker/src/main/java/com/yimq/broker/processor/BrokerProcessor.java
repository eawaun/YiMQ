package com.yimq.broker.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.broker.BrokerController;
import com.yimq.broker.exception.MQBrokerException;
import com.yimq.common.consumer.ConsumerInfo;
import com.yimq.common.message.Message;
import com.yimq.common.message.MessageProto;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.common.protocol.header.RegisterConsumerRequestHeaderProto.RegisterConsumerRequestHeader;
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
            MessageProto.Message messageProto = MessageProto.Message.parseFrom(request.getBody());
            Message message = Message.fromProto(messageProto);
            message.setProducer(ctx.channel().remoteAddress().toString());
            //落地
            boolean result = this.brokerController.getMessageManager().saveMessage(message);
            if (result) {
                //返回成功
                RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request).setCode(ResponseCode.SUCCESS).build();
                return response;
            }
        } catch (Exception e) {
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
