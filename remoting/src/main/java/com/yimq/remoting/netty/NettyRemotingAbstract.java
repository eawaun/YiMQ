package com.yimq.remoting.netty;

import com.yimq.remoting.common.Pair;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import static com.yimq.common.protocol.RemotingCommandType.*;
import static com.yimq.remoting.protocol.RemotingCommandProto.*;
import static com.yimq.common.protocol.ResponseCode.*;

public abstract class NettyRemotingAbstract {
    private final static Logger logger = LoggerFactory.getLogger(NettyRemotingAbstract.class);

    protected final Map<Integer /* requestId */, ResponseFuture> responseTable =
        new ConcurrentHashMap<>(256);

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

    protected RemotingCommand invokeSyncImpl(final Channel channel, final RemotingCommand request
        , final long timeoutMillis) throws InterruptedException, RemotingTimeoutException, RemotingSendRequestException {
        final int requestId = request.getRequestId();

        try {
            final ResponseFuture responseFuture = new ResponseFuture(requestId, timeoutMillis);
            this.responseTable.put(requestId, responseFuture);
            final SocketAddress socketAddress = channel.remoteAddress();
            channel.writeAndFlush(request).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    responseFuture.setSendRequestOK(true);
                } else {
                    responseFuture.setSendRequestOK(false);
                    responseTable.remove(requestId);
                    responseFuture.setCause(future.cause());
                    responseFuture.putResponse(null);
                    logger.warn("invokeSyncImpl: send a request command to channel[{}] failed", socketAddress);
                }
            });

            RemotingCommand responseCommand = responseFuture.waitResponse(timeoutMillis);
            if (null == responseCommand) {
                if (responseFuture.isSendRequestOK()) {
                    throw new RemotingTimeoutException(socketAddress.toString(), timeoutMillis, responseFuture.getCause());
                } else {
                    throw new RemotingSendRequestException(socketAddress.toString(), responseFuture.getCause());
                }
            }

            return responseCommand;
        } finally {
            this.responseTable.remove(requestId);
        }
    }

}
