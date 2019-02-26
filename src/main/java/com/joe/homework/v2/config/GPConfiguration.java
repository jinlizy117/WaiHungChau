package com.joe.homework.v2.config;

import com.joe.homework.v2.mapperRegistry.GPMapperRegistry;

public class GPConfiguration {
    private String configPath;
    private GPMapperRegistry mapperRegistry;

    public GPMapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public GPConfiguration() {
        this.mapperRegistry = new GPMapperRegistry();
    }

    public GPConfiguration scanPath(String path){
        this.configPath = path;
        return this;
    }

    public void build(){
        if(this.configPath == null){
            throw new RuntimeException("Mapper配置路径不能为空");
        }
    }

    public static void main(String[] args) {
        GPConfiguration configration = new GPConfiguration();
        configration.scanPath("H:\\workSpace\\javaDemo\\src\\main\\java\\com\\joe\\homework\\v2\\config\\mapper").build();
    }
}
