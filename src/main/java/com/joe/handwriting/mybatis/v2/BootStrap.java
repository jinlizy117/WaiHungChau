package com.joe.homework.v2;

import com.joe.homework.v2.config.GPConfiguration;
import com.joe.homework.v2.config.mapper.TestMapper;
import com.joe.homework.v2.executor.GPExecutorFactory;
import com.joe.homework.v2.pojo.Test;
import com.joe.homework.v2.sqlSession.GPSqlSession;

public class BootStrap {
    public static void start(){
        GPConfiguration configration = new GPConfiguration();
        configration.scanPath("H:\\workSpace\\javaDemo\\src\\main\\java\\com\\joe\\homework\\v2\\config\\mapper").build();
//        GPSqlSession sqlSession = new GPSqlSession(configration);
        GPSqlSession sqlSession = new GPSqlSession(configration, GPExecutorFactory.ExecutorType.GP_CACHING_EXECUTOR);
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Test test = testMapper.findByPrimaryKey("1");
        System.out.println(test);
    }

    public static void main(String[] args) {
        start();
    }
}
