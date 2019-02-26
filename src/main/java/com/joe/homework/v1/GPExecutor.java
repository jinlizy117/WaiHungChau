package com.joe.homework.v1;

public interface GPExecutor {
    <T>T query(String statement, String parameter);
}
