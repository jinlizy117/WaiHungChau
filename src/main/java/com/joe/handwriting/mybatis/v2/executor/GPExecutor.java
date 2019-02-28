package com.joe.handwriting.mybatis.v2.executor;

import com.joe.handwriting.mybatis.v2.mapperRegistry.GPMapperRegistry;

public interface GPExecutor {

    <T>T query(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception;
}
