package com.test.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope(value = "prototype")
public class ThreadServiceTest {

//    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
     AtomicInteger atomicInteger = new AtomicInteger(1);

    public void test(){
        System.out.println(atomicInteger.getAndIncrement());
    }
}
