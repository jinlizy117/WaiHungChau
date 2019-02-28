package com.joe.homework.v2.mapperRegistry;

import com.joe.homework.v2.pojo.Test;

import java.util.HashMap;
import java.util.Map;

public class GPMapperRegistry {
    private static Map<String, MethodMapperData> methodMapperDataMapping = new HashMap<String, MethodMapperData>();

    public Map<String, MethodMapperData> getMethodMapperData() {
        return methodMapperDataMapping;
    }

    static {
        methodMapperDataMapping.put("com.joe.homework.v2.config.mapper.TestMapper.findByPrimaryKey", new MethodMapperData("select * from Test where id = %d", Test.class));
    }

    public static class MethodMapperData {
        private String sql;
        private Class resultType;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class getResultType() {
            return resultType;
        }

        public void setResultType(Class resultType) {
            this.resultType = resultType;
        }

        public MethodMapperData(String sql, Class resultType) {

            this.sql = sql;
            this.resultType = resultType;
        }
    }
}
