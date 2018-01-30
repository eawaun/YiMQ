package com.yimq.client.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.message.Message;
import com.yimq.common.message.MessageProto;
import com.yimq.remoting.netty.NettyRequestProcessor;
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
        MessageProto.Message messageProto = MessageProto.Message.parseFrom(request.getBody());
        Message message = new Message(messageProto.getTopic(), messageProto.toByteArray());

        ConsumeStatus consumeStatus = messageReceivedListener.consumeMessage(message);
        switch (consumeStatus) {
            case CONSUME_SUCCESS:
                //success
                break;
            case RECONSUME_LATER:
            default:
                //fail
        }
        return null;
    }
}
