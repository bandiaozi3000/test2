package com.test.method;

import com.test.annotation.Valid;
import org.springframework.stereotype.Component;

@Component
public class MyTestMethod {
    @Valid
    public void test() {
        System.out.println("这是一个测试方法");
    }

    public static int f(int value) {
        try {
            return value * value;
        } finally {
           value = 4;
           return value;
        }
    }
}
