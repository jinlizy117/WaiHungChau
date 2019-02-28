package com.joe.homework.v2.proxy;

import com.joe.homework.v2.mapperRegistry.GPMapperRegistry;
import com.joe.homework.v2.sqlSession.GPSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler {
    private GPSqlSession sqlSession;
    /**
     * mappperInterface：
     * 此变量作用详见：org.apache.ibatis.binding.MapperProxyFactory.mapperInterface
     * 与建立代理类中的一级缓存有关
     * 与CachingExecutor的关系未知
     * 过程中涉及到cache建立自mapper.method在configuration.注册中心add mapper时
     */
//    private final Class<T> mappperInterface;

    public MapperProxy(GPSqlSession sqlSession) {
        this.sqlSession = sqlSession;
//        this.mappperInterface = clazz;
    }

    /**
     * 通过方法全名找到mapperData，
     * 通过session使用其中一种具体的查询方式selectOne()，
     * mapperData中的sql与resultType分别作用在statementHandler和RsHandler
     *
     * @param proxy
     * @param method
     * @param args 实际指的是-Mapper.method中的参数，如findById中的id
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getDeclaringClass().getName() +'.'+ method.getName();
        GPMapperRegistry.MethodMapperData methodMapperData =
                sqlSession.getConfiguration().getMapperRegistry().getMethodMapperData().get(methodName);
        //检查是否'将要执行的接口mapper对应的方法'已注册到注册中心
        if(methodMapperData != null){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", methodMapperData.getSql(), args[0]));
            //可根据不同的判断，从入口sqlsession使用不同的方法执行DB操作
            return sqlSession.selectOne(methodMapperData, args[0]);
        }
        return method.invoke(this, args);
    }
}
