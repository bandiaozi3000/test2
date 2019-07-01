package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @RequestMapping(value = "/hystrix")
    @HystrixCommand(fallbackMethod = "onError") //断路30s后尝试执行, 默认为5s)
    public String print() throws Exception {
        throw new Exception();
    }

    public String onError(){
        return "error";
    }
}
