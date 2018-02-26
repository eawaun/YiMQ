package com.yimq.broker;

import com.yimq.broker.mapper.MessageMapper;
import com.yimq.broker.mapper.SqlSessionFactorySingleton;
import com.yimq.common.model.MessageModel;
import org.apache.ibatis.session.SqlSession;

public class MybatisTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionFactorySingleton.getInstance().openSession();
        try {
            MessageMapper mapper = session.getMapper(MessageMapper.class);
            MessageModel messageModel = mapper.select(1);
            System.out.println(messageModel.getId());
        } finally {
            session.close();
        }
    }
}
