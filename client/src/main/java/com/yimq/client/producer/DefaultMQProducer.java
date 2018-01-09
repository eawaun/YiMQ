package com.yimq.client.producer;

import com.yimq.common.message.Message;
import com.yimq.common.protocol.RequestCode;
import com.yimq.common.protocol.route.TopicRouteData;
import com.yimq.remoting.protocol.RemotingCommandProto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand;
import static com.yimq.common.protocol.RequestCode.*;

public class DefaultMQProducer implements MQProducer {

    private Map<String/* topic */, TopicRouteData> topicRouteDataMap = new ConcurrentHashMap<>();

    @Override
    public void sendSync(Message msg) {
        //1、从namesrv得到topic路由信息


        //2、与broker建立连接，发送数据

    }

    private TopicRouteData findTopicRouteData(String topic) {
        TopicRouteData topicRouteData = topicRouteDataMap.get(topic);
        if (topicRouteData != null) {
            return topicRouteData;
        }
        //查询namesrv是否有该topic的路由信息
        RemotingCommand.Builder request = RemotingCommand.newBuilder().setCode(GET_ALL_TOPIC_ROUTE_FROM_NAMESRV);

        return null;
    }
}
