package com.yimq.remoting.netty;

import com.yimq.remoting.RemotingClient;
import com.yimq.remoting.protocol.RemotingCommandProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyRemotingClient extends NettyRemotingAbstract implements RemotingClient {

    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroup;
    private final NettyClientConfig nettyClientConfig;
    private final DefaultEventExecutorGroup defaultEventExecutorGroup;

    private final ConcurrentMap<String/* addr */, Channel> channelTables = new ConcurrentHashMap<>();

    public NettyRemotingClient(final NettyClientConfig nettyClientConfig) {
        this.nettyClientConfig = nettyClientConfig;

        this.eventLoopGroup = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyClientChooser_%d", this.threadIndex.getAndIncrement()));
            }
        });

        this.defaultEventExecutorGroup = new DefaultEventExecutorGroup(this.nettyClientConfig.getWorkerThreads(),
            new ThreadFactory() {
                private AtomicInteger threadIndex = new AtomicInteger(1);
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, String.format("NettyClientWorkerThread_%d", this.threadIndex.getAndIncrement()));
                }
            });

    }

    @Override
    public RemotingCommandProto.RemotingCommand invokeSync(String addr
            , RemotingCommandProto.RemotingCommand request, long timeoutMillis) {
        final Channel channel = this.getChannel(addr);

        return null;
    }

    private Channel getChannel(String addr) {
        if (null == addr) {
            return this.getNamesrvChannel(addr);
        }

        Channel channel = this.channelTables.get(addr);
        if (channel != null && channel.isActive()) {
            return channel;
        }

        return this.createChannel(addr);
    }

    private Channel getNamesrvChannel(String addr) {
        return null;
    }

    private Channel createChannel(String addr) {
        return null;
    }

    @Override
    public void start() {
        this.bootstrap.group(this.eventLoopGroup).channel(NioSocketChannel.class)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, nettyClientConfig.getConnectTimeoutMillis())
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(
                        defaultEventExecutorGroup,
                        new ProtobufVarint32FrameDecoder(),
                        new ProtobufDecoder(RemotingCommandProto.RemotingCommand.getDefaultInstance()),
                        new ProtobufVarint32LengthFieldPrepender(),
                        new ProtobufEncoder(),
                        new NettyClientHandler()
                    );
                }
            });
    }

    @Override
    public void shutdown() {

    }

    class NettyClientHandler extends SimpleChannelInboundHandler<RemotingCommandProto.RemotingCommand> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RemotingCommandProto.RemotingCommand cmd) throws Exception {
            processMessageReceived(ctx, cmd);
        }
    }
}
