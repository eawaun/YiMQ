package com.yimq.common.broker.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.broker.BrokerController;
import com.yimq.common.broker.ConsumerManager;
import com.yimq.common.broker.MessageManager;
import com.yimq.common.exception.MessageHandlerException;
import com.yimq.common.message.Message;
import com.yimq.remoting.netty.NettyRequestProcessor;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import com.yimq.common.protocol.RequestCode;

public class BrokerProcessor implements NettyRequestProcessor {

    private BrokerController brokerController;

    public BrokerProcessor(BrokerController brokerController) {
        this.brokerController = brokerController;
    }

    @Override
    public RemotingCommand process(ChannelHandlerContext ctx, RemotingCommand request) throws InvalidProtocolBufferException {
        switch (request.getCode()) {
            case RequestCode.SEND_MESSAGE:
                return this.processSendMessage(ctx, request);
            case RequestCode.REGISTER_CONSUMER:
                return this.registerConsumer(ctx, request);
        }

        return null;
    }

    private RemotingCommand processSendMessage(ChannelHandlerContext ctx, RemotingCommand request) {
        try {
            String topic = null;
            Message message = new Message(topic, new byte[12]);
            //落地
            this.brokerController.getMessageManager().saveMessage(topic, message);

            //todo 添加到消息队列中，如果是延迟任务，则不添加到队列中，而是放到数据库中
            this.brokerController.getMessageManager().addMessage(topic, message);

            //返回成功
            //todo

        } catch (MessageHandlerException e) {
            e.printStackTrace();
        }


        return null;
    }

    private RemotingCommand registerConsumer(ChannelHandlerContext ctx, RemotingCommand request) {

        return null;
    }
}
