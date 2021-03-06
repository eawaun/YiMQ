package com.yimq.client.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yimq.client.ClientConfig;
import com.yimq.client.ClientInstance;
import com.yimq.client.exception.MQClientException;
import com.yimq.common.Constant;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.header.RegisterConsumerRequestHeaderProto.RegisterConsumerRequestHeader;
import com.yimq.common.protocol.route.BrokerData;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.remoting.common.ThreadFactoryImpl;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultMQPushConsumer extends ClientConfig implements MQPushConsumer {

    private String consumerGroup;
    private String topic;

    private long registerConsumerTimeoutMills = 3000;

    private ClientInstance clientInstance;
    private ExecutorService processRequestTaskExecutor;
    private NettyClientConfig nettyClientConfig;

    private MessageReceivedListener messageReceivedListener;

    public DefaultMQPushConsumer(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    @Override
    public void start() throws InterruptedException, RemotingConnectException, InvalidProtocolBufferException, MQClientException {
        this.nettyClientConfig = new NettyClientConfig();
        this.clientInstance = new ClientInstance(this.getNamesrvAddrList());
        this.processRequestTaskExecutor = Executors.newFixedThreadPool(this.nettyClientConfig.getProcessRequestTaskThreads(),
            new ThreadFactoryImpl("RemotingExecutorThread_"));
        this.clientInstance.getRemotingClient().registerProcessor(new ConsumerProcessor(this.messageReceivedListener)
            , this.processRequestTaskExecutor);
        this.clientInstance.start();
        //注册consumer
        this.registerConsumer();
    }

    @Override
    public void shutdown() {

    }

    private void registerConsumer() throws RemotingConnectException, InterruptedException, InvalidProtocolBufferException, MQClientException {
        RegisterConsumerRequestHeader requestHeader = RegisterConsumerRequestHeader.newBuilder().setConsumerGroup(this.consumerGroup)
            .setTopic(this.topic).build();

        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder(RequestCode.REGISTER_CONSUMER)
            .setCustomHeader(requestHeader.toByteString()).build();

        TopicRouteData topicRouteData = this.clientInstance.findTopicRouteDataFromNamesrv(this.topic);
        List<BrokerData> brokerDatas = topicRouteData.getBrokerDatas();
        for (BrokerData brokerData : brokerDatas) {
            String brokerAddr = brokerData.getBrokerAddrs().get(Constant.MASTER_ID);
            this.clientInstance.sendSync(brokerAddr, request, this.registerConsumerTimeoutMills);
        }
    }

    @Override
    public void subscribe(String topic) {
        this.topic = topic;
    }

    @Override
    public void registerMessageListener(MessageReceivedListener messageReceivedListener) {
        this.messageReceivedListener = messageReceivedListener;
    }


}
