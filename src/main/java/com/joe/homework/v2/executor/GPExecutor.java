package com.joe.homework.v2.executor;

import com.joe.homework.v2.mapperRegistry.GPMapperRegistry;

public interface GPExecutor {

    <T>T query(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception;
}
