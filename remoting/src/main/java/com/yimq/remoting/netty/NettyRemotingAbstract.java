package com.yimq.remoting.netty;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.remoting.common.Pair;
import com.yimq.remoting.common.RemotingUtil;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
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

    protected final Map<Long /* requestId */, ResponseFuture> responseTable =
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

    private void processRequestCommand(ChannelHandlerContext ctx, RemotingCommand request) {

        Runnable run = () -> {
            RemotingCommand response = null;
            try {
                response = defaultRequestProcessor.getLeft().process(ctx, request);
                if (response != null) {
                    ctx.writeAndFlush(response);
                } else {
                    String error = "request code " + request.getCode() + " not supported";
                    response = RemotingCommandBuilder.newResponseBuilder(request, ResponseCode.REQUEST_CODE_NOT_SUPPORTED)
                        .setRemark(error).build();
                    ctx.writeAndFlush(response);
                }
            } catch (Exception e) {
                logger.error("processRequestCommand: Exception", e);
                String errorMessage = "process request exception: " + e.getMessage();
                response = RemotingCommandBuilder.newResponseBuilder(request, ResponseCode.REQUEST_CODE_NOT_SUPPORTED)
                    .setRemark(errorMessage).build();
                ctx.writeAndFlush(response);
            }
        };
        this.defaultRequestProcessor.getRight().submit(run);
    }

    private void processResponseCommand(ChannelHandlerContext ctx, RemotingCommand response) {
        final long requestId = response.getRequestId();
        final ResponseFuture responseFuture = responseTable.get(requestId);
        if (responseFuture != null) {
            responseFuture.setResponseCommand(response);

            responseTable.remove(requestId);

            if (responseFuture.getInvokeCallback() != null) {
                try {
                    responseFuture.executeInvokeCallback(); //run in this thread
                } catch (Exception e) {
                    logger.warn("processResponseCommand: execute invoke callback exception", e);
                } finally {
                    responseFuture.semaphoreRelease();
                }
            } else {
                responseFuture.responseLatchCountDown();
                responseFuture.semaphoreRelease();
            }
        } else {
            logger.warn("processResponseCommand: receive response, but not match any request, addr[{}], remotingCommand[{}]",
                RemotingUtil.channel2Addr(ctx.channel()), response.toString());
        }

    }

    protected RemotingCommand invokeSyncImpl(final Channel channel, final RemotingCommand request
        , final long timeoutMillis) throws InterruptedException, RemotingTimeoutException, RemotingSendRequestException {
        final long requestId = request.getRequestId();

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
                    responseFuture.setResponseCommand(null);
                    responseFuture.responseLatchCountDown();
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
