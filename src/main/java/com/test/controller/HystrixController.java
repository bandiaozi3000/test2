package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class HystrixController {

    @HystrixCommand(fallbackMethod = "onError") //断路30s后尝试执行, 默认为5s)
    public String print() throws Exception {
        Thread.sleep(5000);
        throw new Exception();
    }

    public String onError(){
        return "error";
    }
}
