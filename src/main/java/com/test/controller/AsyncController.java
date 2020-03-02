package com.test.controller;

import com.test.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:AsyncController
 * @Description:异步测试
 * @Author:lm.sun
 * @Date:2019/10/21 10:05
 */
@RestController
@RequestMapping(value = "/async")
public class AsyncController {

    @Autowired
    public TestInterface testInterface;

    @RequestMapping(value = "/test")
    public String test(){
        return testInterface.print("异步任务测试");
    }
}
