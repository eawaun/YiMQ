package com.yimq.namesrv.routeinfo;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yimq.common.protocol.route.BrokerDataProto.BrokerData;
import com.yimq.common.protocol.route.QueueDataProto.QueueData;
import com.yimq.common.protocol.route.TopicRouteDataProto;
import com.yimq.common.protocol.route.TopicRouteDataProto.TopicRouteData;
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
    private Map<String /* topic */, List<QueueData>> topicQueueTable;

    public RouteInfoManager() {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.topicQueueTable = new HashMap<>(1024);
        this.brokerDataTable = new HashMap<>(128);
    }

    public void registerBroker(final String clusterName, final String brokerAddr, final String brokerName) {

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
            return TopicRouteData.newBuilder().setTopic(topic).addAllQueueDatas(queueDatas).
                addAllBrokerDatas(brokerDatas).build();
        }
        return null;
    }
}
