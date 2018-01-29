package com.yimq.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.producer.SendResult;
import com.yimq.common.protocol.header.GetRouteInfoRequestHeaderProto;
import com.yimq.common.protocol.route.BrokerData;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.common.protocol.route.TopicRouteDataProto;
import com.yimq.remoting.common.ThreadFactoryImpl;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingClient;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.yimq.common.protocol.RequestCode.GET_ROUTEINFO_BY_TOPIC;

public class ClientInstance {
    private static final Logger logger = LoggerFactory.getLogger(ClientInstance.class);

    private NettyClientConfig nettyClientConfig;
    private NettyRemotingClient remotingClient;


    private final Map<String/* topic */, TopicRouteData> topicRouteMap = new ConcurrentHashMap<>();

    private final Random random = new Random();
    private final AtomicInteger brokerChooser = new AtomicInteger(random.nextInt());

    public ClientInstance(NettyClientConfig nettyClientConfig) {
        this.nettyClientConfig = nettyClientConfig;
        this.remotingClient = new NettyRemotingClient(this.nettyClientConfig);

    }

    public TopicRouteData findTopicRouteDataFromNamesrv(String topic) throws InterruptedException, RemotingConnectException {
        TopicRouteData topicRouteData = topicRouteMap.get(topic);
        if (topicRouteData != null) {
            return topicRouteData;
        }
        //查询namesrv是否有该topic的路由信息
        GetRouteInfoRequestHeaderProto.GetRouteInfoRequestHeader requestHeader = GetRouteInfoRequestHeaderProto.GetRouteInfoRequestHeader.newBuilder().setTopic(topic).build();
        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder()
            .setCode(GET_ROUTEINFO_BY_TOPIC).setCustomHeader(requestHeader.toByteString()).build();

        RemotingCommand response =
            this.remotingClient.invokeSync(null, request, 3 * 1000);

        try {
            return TopicRouteData.fromProto(TopicRouteDataProto.TopicRouteData.parseFrom(response.getBody()));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BrokerData chooseBroker(String topic) throws RemotingConnectException, InterruptedException {
        TopicRouteData topicRouteData = findTopicRouteDataFromNamesrv(topic);
        List<BrokerData> brokerDatas = topicRouteData.getBrokerDatas();

        int index = Math.abs(this.brokerChooser.getAndIncrement()) % brokerDatas.size();
        return brokerDatas.get(index);
    }

    public SendResult sendSync(final String addr, final RemotingCommand request, final long timeoutMills) throws RemotingConnectException, InterruptedException {
        RemotingCommand response = this.remotingClient.invokeSync(addr, request, timeoutMills);
        return this.processSendResponse(response);
    }

    private SendResult processSendResponse(RemotingCommand response) {
        //todo
        return null;
    }

    public void start() {
        this.remotingClient.start();
    }

    public void shutdown() {
        this.remotingClient.shutdown();
    }

    public NettyRemotingClient getRemotingClient() {
        return remotingClient;
    }

    public void setRemotingClient(NettyRemotingClient remotingClient) {
        this.remotingClient = remotingClient;
    }
}