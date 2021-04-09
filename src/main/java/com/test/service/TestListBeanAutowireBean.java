package com.test.service;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "listBean")
public class TestListBeanAutowireBean {

    @Resource
    private List<TestListBeanAutowireService> list;
}
