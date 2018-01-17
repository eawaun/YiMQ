package com.yimq.remoting.netty;

import com.yimq.remoting.RemotingClient;
import com.yimq.remoting.common.RemotingUtil;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.exception.RemotingSendRequestException;
import com.yimq.remoting.exception.RemotingTimeoutException;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NettyRemotingClient extends NettyRemotingAbstract implements RemotingClient {
    private final static Logger logger = LoggerFactory.getLogger(NettyRemotingClient.class);

    private final static long LOCK_TIMEOUT_MILLIS = 3000;

    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroup;
    private final NettyClientConfig nettyClientConfig;
    private final DefaultEventExecutorGroup defaultEventExecutorGroup;

    private final ConcurrentMap<String/* addr */, ChannelWrapper> channelTables = new ConcurrentHashMap<>();
    private final Lock lockChannelTables = new ReentrantLock();

    private final AtomicReference<String> namesrvAddrChoosed = new AtomicReference<>();
    private final AtomicReference<List<String>> namesrvAddrList = new AtomicReference<>();
    private final Lock lockNamesrvChannel = new ReentrantLock();
    private final AtomicInteger namesrvIndex = new AtomicInteger(initIndex());

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

    public void closeChannel(final String addr, final Channel channel) {

    }

    @Override
    public RemotingCommand invokeSync(String addr
            , RemotingCommand request, long timeoutMillis) throws InterruptedException, RemotingConnectException {
        final Channel channel = this.getChannel(addr);
        if (channel != null && channel.isActive()) {
            RemotingCommand response = null;
            try {
                response = this.invokeSyncImpl(channel, request, timeoutMillis);
                return response;
            } catch (RemotingTimeoutException | RemotingSendRequestException e) {
                logger.warn("invokeSync: {}, so close the channel", e.getMessage());
                this.closeChannel(addr, channel);
            }
        }
        this.closeChannel(addr, channel);
        throw new RemotingConnectException(addr);
    }

    @Override
    public void updateNamesrvAddrList(List<String> addrs) {
        List<String> old = this.namesrvAddrList.get();
        boolean updated = false;

        if (!addrs.isEmpty()) {
            if (null == old) {
                updated = true;
            } else if (old.size() != addrs.size()) {
                updated = true;
            } else {
                for (String addr : addrs) {
                    if (!old.contains(addr)) {
                        updated = true;
                        break;
                    }
                }
            }

            if (updated) {
                Collections.shuffle(addrs);
                this.namesrvAddrList.set(addrs);
            }
        }
    }

    private static int initIndex() {
        Random r = new Random();
        return Math.abs(r.nextInt() % 999);
    }

    private Channel getChannel(String addr) throws InterruptedException {
        if (null == addr) {
            return this.getNamesrvChannel();
        }

        ChannelWrapper cw = this.channelTables.get(addr);
        if (cw != null && cw.isActive()) {
            return cw.getChannel();
        }

        return this.createChannel(addr);
    }

    private Channel getNamesrvChannel() throws InterruptedException {
        String addr = this.namesrvAddrChoosed.get();
        if (addr != null) {
            ChannelWrapper cw = this.channelTables.get(addr);
            if (cw != null && cw.isActive()) {
                return cw.getChannel();
            }
        }

        final List<String> addrList = this.namesrvAddrList.get();
        if (this.lockNamesrvChannel.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            try {
                addr = this.namesrvAddrChoosed.get();
                if (addr != null) {
                    ChannelWrapper cw = this.channelTables.get(addr);
                    if (cw != null && cw.isActive()) {
                        return cw.getChannel();
                    }
                }

                if (addrList != null && !addrList.isEmpty()) {
                    for (int i = 0; i < addrList.size(); i++) {
                        int index = this.namesrvIndex.incrementAndGet();
                        index = Math.abs(index);//防止溢出
                        index = index % addrList.size();
                        String newAddr = addrList.get(index);

                        this.namesrvAddrChoosed.set(newAddr);

                        Channel channel = this.createChannel(newAddr);
                        if (channel != null) {
                            return channel;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("getNamesrvChannel: create name server channel exception", e);
            } finally {
                this.lockNamesrvChannel.unlock();
            }
        } else {
            logger.warn("getNamesrvChannel: try to lock name server channel, but timeout, {}ms", LOCK_TIMEOUT_MILLIS);
        }
        return null;
    }

    private Channel createChannel(String addr) throws InterruptedException {
        ChannelWrapper cw = this.channelTables.get(addr);
        if (cw != null && cw.isActive()) {
            cw.getChannel().close();
            this.channelTables.remove(addr);
        }

        if (this.lockChannelTables.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            try {
                boolean createNewChannel;
                cw = this.channelTables.get(addr);
                if (cw != null) {
                    if (cw.isActive()) {
                        cw.getChannel().close();
                        this.channelTables.remove(addr);
                        createNewChannel = true;
                    } else if (!cw.getChannelFuture().isDone()) {
                        createNewChannel = false;
                    } else {
                        this.channelTables.remove(addr);
                        createNewChannel = true;
                    }
                } else {
                    createNewChannel = true;
                }

                if (createNewChannel) {
                    ChannelFuture channelFuture = this.bootstrap.connect(RemotingUtil.string2SocketAddress(addr));
                    logger.info("createChannel: begin to connect remote host[{}] asynchronously", addr);
                    cw = new ChannelWrapper(channelFuture);
                    this.channelTables.putIfAbsent(addr, cw);
                }
            } catch (Exception e) {
                logger.error("createChannel: create channel exception", e);
            } finally {
                this.lockChannelTables.unlock();
            }
        } else {
            logger.warn("createChannel: try to lock channel table, but timeout, {}ms", LOCK_TIMEOUT_MILLIS);
        }

        if (cw != null) {
            ChannelFuture channelFuture = cw.getChannelFuture();
            if (channelFuture.awaitUninterruptibly(this.nettyClientConfig.getConnectTimeoutMillis())) {
                if (cw.isActive()) {
                    logger.info("createChannel: connect remote host[{}] successfully, {}", addr, channelFuture.toString());
                    return cw.getChannel();
                } else {
                    logger.warn("createChannel: connect remote host[{}] fail, {}", addr, channelFuture.toString(),
                        channelFuture.cause());
                }
            } else {
                logger.warn("createChannel: connect remote host[{}] but timeout[{}ms], {}", addr,
                    this.nettyClientConfig.getConnectTimeoutMillis(), channelFuture.toString());
            }
        }

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
                        new ProtobufDecoder(RemotingCommand.getDefaultInstance()),
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

    class NettyClientHandler extends SimpleChannelInboundHandler<RemotingCommand> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RemotingCommand cmd) throws Exception {
            processMessageReceived(ctx, cmd);
        }
    }

    static class ChannelWrapper {
        private final ChannelFuture channelFuture;

        public ChannelWrapper(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }

        public boolean isActive() {
            return this.channelFuture.channel() != null && this.channelFuture.channel().isActive();
        }

        public boolean isWritable() {
            return this.channelFuture.channel().isWritable();
        }

        public Channel getChannel() {
            return this.channelFuture.channel();
        }

        public ChannelFuture getChannelFuture() {
            return channelFuture;
        }
    }
}
