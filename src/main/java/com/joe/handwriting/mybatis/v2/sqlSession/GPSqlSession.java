package com.joe.handwriting.mybatis.v2.sqlSession;


import com.joe.handwriting.mybatis.v2.config.GPConfiguration;
import com.joe.handwriting.mybatis.v2.executor.GPExecutor;
import com.joe.handwriting.mybatis.v2.proxy.MapperProxy;
import com.joe.handwriting.mybatis.v2.executor.GPExecutorFactory;
import com.joe.handwriting.mybatis.v2.mapperRegistry.GPMapperRegistry;

import java.lang.reflect.Proxy;

public class GPSqlSession {
    private GPExecutor executor;
    private GPConfiguration configuration;

    public GPConfiguration getConfiguration() {
        return configuration;
    }

    public GPSqlSession(GPConfiguration configuration, GPExecutorFactory.ExecutorType executorType) {
        this.configuration = configuration;
        this.executor = GPExecutorFactory.create(configuration, executorType);
    }

    public GPSqlSession(GPConfiguration configuration) {
        this.configuration = configuration;
        this.executor = GPExecutorFactory.create(configuration);
    }

    /**
     *
     * @param clazz 特指mapper的类型
     * @param <T>
     * @return
     */
    public <T>T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this));
    }

    public <T>T selectOne(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }
}
