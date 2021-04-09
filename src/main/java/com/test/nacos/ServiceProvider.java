package com.test.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ServiceProvider {

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr","127.0.0.1:8848");
        properties.setProperty("namespace","0574b38c-2dd1-4999-a09a-3468af14e679");
        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.registerInstance("service1","default","172.19.109.147",8094,"cluster1");
        namingService.registerInstance("service1","default","172.19.109.147",8095,"cluster1");
        List<Instance> instances = namingService.getAllInstances("service1","default");
        System.out.println(instances);
        try{
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
