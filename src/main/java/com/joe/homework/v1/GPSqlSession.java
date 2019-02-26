package com.joe.homework.v1;


public class GPSqlSession {
    public GPSqlSession(GPExecutor executor, GPConfiguration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }

    private GPExecutor executor;
    private GPConfiguration configuration;



    public <T>T getMapper(Class<T> clazz){
       return (T)configuration.getMapper(clazz, this);
    }

    public <T>T selectOne(String statement, String parameter){
       return executor.query( statement, parameter);
    }

}
