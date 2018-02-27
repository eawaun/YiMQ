package com.yimq.broker;

import com.yimq.broker.mapper.MessageMapper;
import com.yimq.broker.mapper.SqlSessionFactorySingleton;
import com.yimq.broker.model.MessagePO;
import com.yimq.common.message.Message;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionFactorySingleton.getInstance().openSession();
        try {
            MessageMapper mapper = session.getMapper(MessageMapper.class);
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
            System.out.println(mapper.save(messagePO));
        } finally {
            session.close();
        }
    }
}
