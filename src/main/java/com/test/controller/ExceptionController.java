package com.test.controller;

import com.test.exception.MyException;
import com.test.method.MyTestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ExceptionController {
    @Autowired
    MyTestMethod myTestMethod;

    @RequestMapping(value = "/test1")
    public String test1() {
        System.out.println("dadasdas");
        return "test1";
    }

    @Valid()
    @RequestMapping(value = "/test2/{id}")
    public String test2(@PathVariable("id") int id) throws MyException, NoSuchMethodException {
        myTestMethod.test();
        if (id == 1) {
            throw new MyException("001", "测试异常");
        } else if (id == 2) {
            throw new MyException("002", "测试异常");
        }
        return "test1";
    }

}
