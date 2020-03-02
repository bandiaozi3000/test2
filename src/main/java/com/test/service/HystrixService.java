package com.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class HystrixService {

    @HystrixCommand(
            fallbackMethod = "onError",
            threadPoolProperties = {  //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"), //命令执行超时时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"), //若干10s一个窗口内失败三次, 则达到触发熔断的最少请求量
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //断路30s后尝试执行, 默认为5s
            })
    public String print(int a) throws Exception {
        int b = 1/0;
        if(a==1){
            Thread.sleep(5000);
        }else{
            Thread.sleep(2000);
        }
        return "success";
    }

    public String onError(int a) {
        System.out.println(Thread.currentThread().getName());
        return "error";
    }
}
