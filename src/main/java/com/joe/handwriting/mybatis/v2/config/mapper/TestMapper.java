package com.joe.homework.v2.config.mapper;

import com.joe.homework.v2.pojo.Test;

public interface TestMapper {
    public Test findByPrimaryKey(String userId);
}
