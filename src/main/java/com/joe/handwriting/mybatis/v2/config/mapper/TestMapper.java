package com.joe.handwriting.mybatis.v2.config.mapper;

import com.joe.handwriting.mybatis.v2.pojo.Test;

public interface TestMapper {
    public Test findByPrimaryKey(String userId);
}
