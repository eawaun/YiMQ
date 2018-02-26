package com.yimq.broker.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactorySingleton {
    private static class SingletonHolder {
        private static final SqlSessionFactory INSTANCE = buildSqlSessionFactory();


        private static SqlSessionFactory buildSqlSessionFactory() {
            String resource = "mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                return new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private SqlSessionFactorySingleton() {}

    public static SqlSessionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
