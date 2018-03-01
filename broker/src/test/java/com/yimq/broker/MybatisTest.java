package com.yimq.broker;

import com.yimq.broker.mapper.MessageMapper;
import com.yimq.broker.mapper.SqlSessionFactorySingleton;
import com.yimq.broker.model.MessagePO;
import com.yimq.common.message.Message;
import com.yimq.common.util.TimeUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MybatisTest {
    public static void main(String[] args) {
        MessagePO messagePO = new MessagePO();
        messagePO.setId(100L);
        messagePO.setConsumerList("");
        messagePO.setContent(new byte[]{});
        messagePO.setCreateTime(1);
        messagePO.setDelayTime(1);
        messagePO.setProducer("");
        messagePO.setQueueId(1);
        messagePO.setRetryCount(1);
        messagePO.setStatus(1);
        messagePO.setTopic("");
        messagePO.setUpdateTime(1);

        SqlSession session1 = SqlSessionFactorySingleton.getInstance().openSession(true);
        SqlSession session2 = SqlSessionFactorySingleton.getInstance().openSession(true);
        SqlSession session3 = SqlSessionFactorySingleton.getInstance().openSession(true);

        MessageMapper mapper1 = session1.getMapper(MessageMapper.class);
        mapper1.selectByStatus(5).forEach(item -> System.out.println("!!11!!!" + item.getId()));
        mapper1.selectByStatus(5).forEach(item -> System.out.println("!!11!!!" + item.getId()));
//        session1.commit();

        MessageMapper mapper3 = session3.getMapper(MessageMapper.class);
        mapper3.selectByStatus(5).forEach(item -> System.out.println("!!33!!!" + item.getId()));
        session3.commit();
        try {
            MessageMapper mapper2 = session2.getMapper(MessageMapper.class);
            mapper2.updateStatusByStatus(600299062624256L, 6, 5, 1519796621);
        } finally {
            session2.close();
        }
        mapper3.selectByStatus(5).forEach(item -> System.out.println("!!33!!!" + item.getId()));
    }
}
