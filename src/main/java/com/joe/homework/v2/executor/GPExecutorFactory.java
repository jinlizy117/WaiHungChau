package com.joe.homework.v2.executor;


import com.joe.homework.v2.config.GPConfiguration;

public class GPExecutorFactory {

    public static GPExecutor create(GPConfiguration configuration, ExecutorType executorType){
        switch (executorType){
            case GP_SIMPLE_EXECUTOR:
                return new GPSimpleExecutor(configuration);
            case GP_CACHING_EXECUTOR:
                return new GPCachingExecutor(new GPSimpleExecutor(configuration));
            default:
               throw new RuntimeException("没有对应执行器");
        }
    }

    public static GPExecutor create(GPConfiguration configuration){
        return create(configuration, ExecutorType.GP_SIMPLE_EXECUTOR);
    }

    public enum ExecutorType{
        GP_SIMPLE_EXECUTOR,GP_CACHING_EXECUTOR
    }
}
