package com.joe.handwriting.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


public class GPConfiguration {
    public <T>T getMapper(Class<T> clazz, GPSqlSession session) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(session));
    }


    static class TestMapperXml{
        public static final String namespace = "TestMapper";

        public static final Map<String, String> methodSqlMapping = new HashMap<String, String>();
        static{
            methodSqlMapping.put("selectByPrimaryKey", "select * from Test where id = %d");
        }
    }
}
