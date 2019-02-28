package com.joe.homework.v2.executor;

import com.joe.homework.v2.config.GPConfiguration;
import com.joe.homework.v2.mapperRegistry.GPMapperRegistry;
import com.joe.homework.v2.statementHandler.GPStatementHandler;

public class GPSimpleExecutor implements GPExecutor {
    private GPStatementHandler statementHandler;
    private GPConfiguration configuration;

    public GPConfiguration getConfiguration() {
        return configuration;
    }

    public GPSimpleExecutor(GPConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T>T query(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception {
        GPStatementHandler statementHandler = new GPStatementHandler(configuration);
        return statementHandler.<T>handler(mapperData, parameter);
    }
}
