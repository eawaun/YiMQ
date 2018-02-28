package com.yimq.client.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.message.Message;
import com.yimq.common.message.MessageProto;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import com.yimq.common.protocol.RequestCode;
import io.netty.channel.ChannelHandlerContext;

public class ConsumerProcessor implements NettyRequestProcessor {

    private MessageReceivedListener messageReceivedListener;

    public ConsumerProcessor(MessageReceivedListener messageReceivedListener) {
        this.messageReceivedListener = messageReceivedListener;
    }

    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        switch (request.getCode()) {
            case RequestCode.CONSUME_MESSAGE_DIRECTLY:
                return this.consumeMessageDirectly(ctx, request);
        }

        return null;
    }

    private RemotingCommand consumeMessageDirectly(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        Message message = Message.fromProto(MessageProto.Message.parseFrom(request.getBody()));

        ConsumeStatus consumeStatus = messageReceivedListener.consumeMessage(message);
        switch (consumeStatus) {
            case CONSUME_SUCCESS:
                RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request, ResponseCode.SUCCESS).build();
                return response;
            case RECONSUME_LATER:
            default:
        }
        RemotingCommand response = RemotingCommandBuilder.newResponseBuilder(request, ResponseCode.SYSTEM_BUSY).build();
        return response;
    }
}
