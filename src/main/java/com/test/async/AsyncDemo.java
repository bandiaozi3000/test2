package com.test.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsyncDemo {

    @Async
    public void print() throws InterruptedException {
        async();
        System.out.println("这是一个异步测试方法.........");
    }


    public void async() throws InterruptedException {
        System.out.println("方法异步进行中......");
        Thread.sleep(5000);
        System.out.println("方法异步进行结束......");
    }
}
