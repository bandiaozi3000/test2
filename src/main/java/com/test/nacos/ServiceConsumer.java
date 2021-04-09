package com.test.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.Properties;

public class ServiceConsumer {
    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr","127.0.0.1:8848");
        properties.setProperty("namespace","0574b38c-2dd1-4999-a09a-3468af14e679");
        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.subscribe("service1", "default",new EventListener() {
            @Override
            public void onEvent(Event event) {
                mockConsume(namingService,"service1","default");
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mockConsume(NamingService namingService,String serviceName,String groupName) {
        int i=0,loop = 5;
        try{
            while(i++<loop){
                Instance instance = namingService.selectOneHealthyInstance(serviceName,groupName);
                if(instance!=null){
                    System.out.println(instance.toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
