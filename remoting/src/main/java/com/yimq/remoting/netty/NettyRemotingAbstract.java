package com.yimq.remoting.netty;

import com.yimq.remoting.common.Pair;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;

import static com.yimq.common.protocol.RemotingCommandType.*;
import static com.yimq.remoting.protocol.RemotingCommandProto.*;
import static com.yimq.common.protocol.ResponseCode.*;

public abstract class NettyRemotingAbstract {

    protected Pair<NettyRequestProcessor, ExecutorService> defaultRequestProcessor;

    public void processMessageReceived(ChannelHandlerContext ctx, RemotingCommand cmd) {
        if (cmd != null) {
            switch (cmd.getType()) {
                case REQUEST_COMMAND:
                    processRequestCommand(ctx, cmd);
                    break;
                case RESPONSE_COMMAND:
                    processResponseCommand(ctx, cmd);
                    break;
                default:
                    break;
            }
        }
    }

    private void processRequestCommand(ChannelHandlerContext ctx, RemotingCommand cmd) {

        Runnable run = () -> {
            RemotingCommand.Builder responseBuilder = defaultRequestProcessor.getObj1().process(ctx, cmd);
            if (responseBuilder != null) {
                responseBuilder.setType(RESPONSE_COMMAND);
                RemotingCommand response = responseBuilder.build();
                ctx.writeAndFlush(response);
            } else {
                String error = "request code " + cmd.getCode() + " not supported";
                RemotingCommand response = RemotingCommand.newBuilder().setType(RESPONSE_COMMAND)
                    .setCode(REQUEST_CODE_NOT_SUPPORTED).setRemark(error).build();
                ctx.writeAndFlush(response);
            }
        };
        this.defaultRequestProcessor.getObj2().submit(run);
    }

    private void processResponseCommand(ChannelHandlerContext ctx, RemotingCommand cmd) {

    }

}
