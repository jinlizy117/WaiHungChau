package com.joe.homework.v1;

public class Entry {
    public static void main(String[] args) {
        GPSqlSession session = new GPSqlSession(new GPSimpleExecutor(), new GPConfiguration());
        TestMapper testMapper = session.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey("1");
        System.out.println(test);
    }
}
