package com.yimq.remoting.netty;

import com.yimq.remoting.RemotingServer;
import com.yimq.remoting.common.Pair;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandProto;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyRemotingServer extends NettyRemotingAbstract implements RemotingServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyRemotingServer.class);

    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final NettyServerConfig nettyServerConfig;

    private int port = 0;

    public NettyRemotingServer(final NettyServerConfig nettyServerConfig) {
        this.nettyServerConfig = nettyServerConfig;
        this.serverBootstrap = new ServerBootstrap();

        this.bossGroup = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyBoss_%d", this.threadIndex.getAndIncrement()));
            }
        });

        this.workerGroup = new NioEventLoopGroup(this.nettyServerConfig.getWorkerThreads(), new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(1);
            private int threadTotal = nettyServerConfig.getWorkerThreads();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyWorker_threadTotal[%d]_%d", threadTotal, this.threadIndex.getAndIncrement()));
            }
        });
    }

    @Override
    public void start() {
        this.serverBootstrap.group(this.bossGroup, this.workerGroup)
            .channel(NioServerSocketChannel.class)
            .localAddress(new InetSocketAddress(this.nettyServerConfig.getListenPort()))
            .handler(new LoggingHandler())
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(RemotingCommand.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new NettyServerHandler());
                }
            });
        try {
            ChannelFuture channelFuture = this.serverBootstrap.bind().sync();
            InetSocketAddress addr = (InetSocketAddress) channelFuture.channel().localAddress();
            this.port = addr.getPort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void registerProcessor(NettyRequestProcessor processor, ExecutorService executorService) {
        this.defaultRequestProcessor = new Pair<>(processor, executorService);
    }

    @Override
    public RemotingCommand invokeSync(Channel channel, RemotingCommand request, long timeoutMills) throws InterruptedException, RemotingSendRequestException, RemotingTimeoutException {
        return this.invokeSyncImpl(channel, request, timeoutMills);
    }

    class NettyServerHandler extends SimpleChannelInboundHandler<RemotingCommand> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RemotingCommand msg) throws Exception {
            processMessageReceived(ctx, msg);
        }
    }

}
