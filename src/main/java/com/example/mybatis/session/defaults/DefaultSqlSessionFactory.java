package com.example.mybatis.session.defaults;

import com.example.mybatis.session.Configuration;
import com.example.mybatis.session.SqlSession;
import com.example.mybatis.session.SqlSessionFactory;

/**
 * 处理开启 SqlSession 时，对 DefaultSqlSession 的创建以及传递 mapperRegistry，这样就可以在使用 SqlSession 时获取每个代理类的映射器对象了
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
