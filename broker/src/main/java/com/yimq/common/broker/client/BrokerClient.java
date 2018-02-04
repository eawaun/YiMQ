package com.yimq.common.broker.client;

import com.yimq.common.broker.exception.MQBrokerException;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.ResponseCode;
import com.yimq.common.protocol.header.RegisterBrokerRequestHeaderProto.RegisterBrokerRequestHeader;
import com.yimq.common.topic.TopicConfigProto;
import com.yimq.common.topic.TopicConfigWrapper;
import com.yimq.remoting.RemotingClient;
import com.yimq.remoting.exception.RemotingConnectException;
import com.yimq.remoting.netty.NettyClientConfig;
import com.yimq.remoting.netty.NettyRemotingClient;
import com.yimq.remoting.protocol.RemotingCommandBuilder;
import com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrokerClient {
    private final static Logger logger = LoggerFactory.getLogger(BrokerClient.class);

    private RemotingClient remotingClient;

    public BrokerClient(NettyClientConfig nettyClientConfig) {
        this.remotingClient = new NettyRemotingClient(nettyClientConfig);
    }

    public void start() {
        this.remotingClient.start();
    }

    public void registerBrokerToAllNamesrv(
        final String clusterName,
        final String brokerAddr,
        final String brokerName,
        final int brokerId,
        final TopicConfigWrapper topicConfigWrapper,
        final long timeoutMills) {
        List<String> namesrvAddrs = this.remotingClient.getNamesrvAddrs();

        if (!CollectionUtils.isEmpty(namesrvAddrs)) {
            for (String namesrvAddr : namesrvAddrs) {
                try {
                    this.registerBroker(namesrvAddr, clusterName, brokerName, brokerAddr, brokerId, topicConfigWrapper, timeoutMills);
                    logger.info("registerBrokerToAllNamesrv: register to name server[{}] successfully!", namesrvAddr);
                } catch (Exception e) {
                    logger.warn("registerBrokerToAllNamesrv: register to name server[{}] fail!", namesrvAddr, e);
                }
            }

        }
    }

    private void registerBroker(
        final String namesrvAddr,
        final String clusterName,
        final String brokerName,
        final String brokerAddr,
        final int brokerId,
        final TopicConfigWrapper topicConfigWrapper,
        final long timeoutMills) throws RemotingConnectException, InterruptedException, MQBrokerException {
        TopicConfigProto.TopicConfigWrapper topicConfigWrapperProto = topicConfigWrapper.toProto();

        RegisterBrokerRequestHeader requestHeader = RegisterBrokerRequestHeader.newBuilder().setClusterName(clusterName)
            .setBrokerName(brokerName).setBrokerAddr(brokerAddr).setBrokerId(brokerId).build();

        RemotingCommand request = RemotingCommandBuilder.newRequestBuilder(RequestCode.REGISTER_BROKER)
            .setCustomHeader(requestHeader.toByteString()).setBody(topicConfigWrapperProto.toByteString()).build();

        RemotingCommand response = this.remotingClient.invokeSync(namesrvAddr, request, timeoutMills);
        if (response != null) {
            switch (response.getCode()) {
                case ResponseCode.SUCCESS:
                    return;
                default:
                    throw new MQBrokerException(response.getCode(), response.getRemark());
            }
        }
        throw new MQBrokerException("response is null!");
    }

    public void updateNamesrvAddrList(final String addrs) {
        List<String> list = Stream.of(addrs.split(";")).collect(Collectors.toList());
        this.remotingClient.updateNamesrvAddrs(list);
    }
}
