package com.yimq.namesrv.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yimq.common.topic.BrokerTopicConfigWrapper;
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

public class RouteInfoManager {
    private final static Logger logger = LoggerFactory.getLogger(RouteInfoManager.class);

    private final ReadWriteLock readWriteLock;
    private Map<String/* brokerName */, BrokerData> brokerDataTable;
    private Map<String/* topic */, List<QueueData>> topicQueueTable;
    private Map<String/* clusterName */, Set<String>> clusterAddrTable;
    private Map<String/* brokerAddr */, BrokerLiveInfo> brokerLiveTable;

    public RouteInfoManager() {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.topicQueueTable = new HashMap<>(1024);
        this.brokerDataTable = new HashMap<>(128);
        this.clusterAddrTable = new HashMap<>();
        this.brokerLiveTable = new HashMap<>(128);
    }

    public void registerBroker(final String clusterName, final String brokerAddr, final String brokerName,
                               final int brokerId, final BrokerTopicConfigWrapper brokerTopicConfigWrapper,
                               final Channel channel) {
        try {
            this.readWriteLock.writeLock().lockInterruptibly();

            Set<String> brokerNames = this.clusterAddrTable.get(clusterName);
            if (CollectionUtils.isEmpty(brokerNames)) {
                brokerNames = Sets.newHashSet();
                this.clusterAddrTable.put(clusterName, brokerNames);
            }
            brokerNames.add(brokerName);//add broker to broker cluster

            boolean registerFirst = false;
            BrokerData brokerData = this.brokerDataTable.get(brokerName);
            if (brokerData == null) {//broker doesn't exist so need to register
                registerFirst = true;
                brokerData = new BrokerData(clusterName, brokerName, Maps.newHashMap());
                this.brokerDataTable.put(brokerName, brokerData);
            }
            String oldAddr = brokerData.getBrokerAddrs().put(brokerId, brokerAddr);
            registerFirst = registerFirst || (oldAddr == null);//if broker id doesn't exist before

            if (brokerTopicConfigWrapper != null) {//add broker's queue to topicQueueTable
                if (registerFirst) {
                    Map<String, TopicConfig> topicConfigMap = brokerTopicConfigWrapper.getBrokerTopicConfigMap();
                    if (topicConfigMap != null) {
                        topicConfigMap.values().forEach(topicConfig -> this.createOrUpdateQueueData(brokerName, topicConfig));
                    }
                }
            }

            //save live broker
            BrokerLiveInfo brokerLiveInfo = new BrokerLiveInfo(System.currentTimeMillis(), channel);
            BrokerLiveInfo prevBrokerLiveInfo = this.brokerLiveTable.put(brokerAddr, brokerLiveInfo);
            if (prevBrokerLiveInfo == null) {
                logger.info("registerBroker: a new broker has been registered, addr[{}]", brokerAddr);
            }

        } catch (InterruptedException e) {
            logger.error("registerBroker: writeLock interrupted exception", e);
        }  finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    private void createOrUpdateQueueData(final String brokerName, final TopicConfig topicConfig) {
        QueueData queueData = new QueueData(brokerName, topicConfig.getReadQueueNums(), topicConfig.getWriteQueueNums(),
            topicConfig.getPermission());

        List<QueueData> queueDatas = this.topicQueueTable.get(topicConfig.getTopic());
        if (CollectionUtils.isEmpty(queueDatas)) {
            queueDatas = Lists.newArrayList(queueData);
            this.topicQueueTable.put(topicConfig.getTopic(), queueDatas);
        } else {
            for (QueueData old : queueDatas) {
                if (brokerName.equals(old.getBrokerName()) && !queueData.equals(old)) {
                    queueDatas.remove(old);
                    queueDatas.add(queueData);
                    logger.info("createOrUpdateQueueData: topic({}) queue has been updated, old: {}, new: {}",
                        topicConfig.getTopic(), old, queueData);
                }
            }
        }
    }

    public TopicRouteData getTopicRouteDataByTopic(final String topic) {
        boolean foundQueueData = false;
        boolean foundBrokerData = false;

        List<BrokerData> brokerDatas = new ArrayList<>();
        Set<String> brokerNameSet = new HashSet<>();
        List<QueueData> queueDatas = null;
        try {
            this.readWriteLock.readLock().lockInterruptibly();
            queueDatas = this.topicQueueTable.get(topic);
            if (!CollectionUtils.isEmpty(queueDatas)) {
                foundQueueData = true;

                queueDatas.forEach(queueData -> brokerNameSet.add(queueData.getBrokerName()));

                for (String brokerName : brokerNameSet) {
                    BrokerData brokerData = this.brokerDataTable.get(brokerName);
                    if (brokerData != null) {
                        brokerDatas.add(brokerData);
                        foundBrokerData = true;
                    }
                }
            }
        } catch (InterruptedException e) {
            logger.error("getTopicRouteDataByTopic: readLock interrupted exception", e);
        } finally {
            this.readWriteLock.readLock().unlock();
        }

        if (foundQueueData && foundBrokerData) {
            return new TopicRouteData(topic, brokerDatas, queueDatas);
        }
        return null;
    }
}

class BrokerLiveInfo {
    private long lastUpdateTimestamp;
    private Channel channel;

    public BrokerLiveInfo(long lastUpdateTimestamp, Channel channel) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}