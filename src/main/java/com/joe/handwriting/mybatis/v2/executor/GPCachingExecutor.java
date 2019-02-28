package com.joe.handwriting.mybatis.v2.executor;

import com.joe.handwriting.mybatis.v2.config.GPConfiguration;
import com.joe.handwriting.mybatis.v2.mapperRegistry.GPMapperRegistry;

import java.util.HashMap;
import java.util.Map;

public class GPCachingExecutor implements GPExecutor {
    private GPSimpleExecutor simpleExecutor;
    private GPConfiguration configuration;

    /**
     * 其中的'Object'是返回结果的类型,缓存的是methodName与结果的映射
     * 其中的'String'是sql，直接以操作DB的语言作为缓存key，更贴近事务
     * 原名：mapperCachingMapping
     */
    private Map <String, Object>mapperCachingMapping = new <String, Object>HashMap();

    public GPCachingExecutor(GPSimpleExecutor simpleExecutor) {
        this.simpleExecutor = simpleExecutor;
        this.configuration = simpleExecutor.getConfiguration();
    }

    @Override
    public <T> T query(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception  {
        Object result = null;
        if(mapperCachingMapping.containsKey(mapperData)){
            result = mapperCachingMapping.get(mapperData);
            if(mapperCachingMapping.get(mapperData) != null){
                System.out.println("缓存命中");
                return (T)result;
            }
        }
        result = simpleExecutor.query(mapperData, parameter);
        mapperCachingMapping.put(mapperData.getSql(), result);
        return (T)result;
    }

}
