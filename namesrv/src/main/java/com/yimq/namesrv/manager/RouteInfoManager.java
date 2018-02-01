package com.yimq.namesrv.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yimq.common.topic.TopicConfigWrapper;
import com.yimq.common.protocol.route.BrokerData;
import com.yimq.common.protocol.route.QueueData;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.common.topic.TopicConfig;
import io.netty.channel.Channel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RouteInfoManager {
    private final static Logger logger = LoggerFactory.getLogger(RouteInfoManager.class);

    private final ReadWriteLock readWriteLock;
    private Map<String/* brokerName */, BrokerData> brokerDataTable;
    private Map<String/* topic */, TopicConfig> topicTable;
    private Map<String/* topic */, Set<String>>  topicBrokerTable;
    private Map<String/* clusterName */, Set<String>> clusterBrokerTable;

    public RouteInfoManager() {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.topicTable = new HashMap<>(1024);
        this.brokerDataTable = new HashMap<>(128);
        this.topicBrokerTable = new HashMap<>(1024);
        this.clusterBrokerTable = new HashMap<>();
    }

    public void registerBroker(final String clusterName, final String brokerAddr, final String brokerName,
                               final int brokerId, final TopicConfigWrapper topicConfigWrapper,
                               final Channel channel) {
        try {
            this.readWriteLock.writeLock().lockInterruptibly();

            Set<String> brokerNames = this.clusterBrokerTable.get(clusterName);
            if (CollectionUtils.isEmpty(brokerNames)) {
                brokerNames = Sets.newHashSet();
                this.clusterBrokerTable.put(clusterName, brokerNames);
            }
            brokerNames.add(brokerName);//add broker to broker cluster

            boolean registerFirst = false;
            BrokerData brokerData = this.brokerDataTable.get(brokerName);
            if (brokerData == null) {
                registerFirst = true;
                brokerData = new BrokerData(clusterName, brokerName, Maps.newHashMap());
                this.brokerDataTable.put(brokerName, brokerData);
            }
            brokerData.getBrokerAddrs().put(brokerId, brokerAddr);

            if (topicConfigWrapper != null) {//add default topic to broker
                if (registerFirst) {//slave不需要再注册一次topic
                    Map<String, TopicConfig> topicConfigMap = topicConfigWrapper.getBrokerTopicConfigMap();
                    if (topicConfigMap != null) {
                        topicConfigMap.forEach((topic, topicConfig) -> this.topicTable.put(topic, topicConfig));
                    }
                }
            }
        } catch (InterruptedException e) {
            logger.error("registerBroker: writeLock interrupted exception", e);
        }  finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    public TopicRouteData getTopicRouteDataByTopic(final String topic) {
        TopicConfig topicConfig = this.topicTable.get(topic);
        if (topicConfig == null) {
            return null;
        }

        Set<String> brokerNames = this.topicBrokerTable.get(topic);
        if (CollectionUtils.isEmpty(brokerNames)) {
            return null;
        }

        List<BrokerData> brokerDatas = brokerNames.stream().map(brokerName -> brokerDataTable.get(brokerName))
            .collect(Collectors.toList());
        return new TopicRouteData(topic, topicConfig, brokerDatas);
    }
}