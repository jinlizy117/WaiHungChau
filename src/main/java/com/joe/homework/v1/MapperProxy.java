package com.joe.homework.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {
    private GPSqlSession session;

    public MapperProxy(GPSqlSession session) {
        this.session = session;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(GPConfiguration.TestMapperXml.namespace)){
            String sql = GPConfiguration.TestMapperXml.methodSqlMapping.get(method.getName());
            return session.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }
}
