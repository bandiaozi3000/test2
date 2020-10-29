package com.test.dubbotest;

public class AobingServiceImpl implements AobingService {
    public String hello(String name) {  
        return "Yo man Hello，I am" + name;  
    }  
}  