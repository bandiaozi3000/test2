package com.test.bean;

import java.util.HashMap;
import java.util.Map;


public class ClassA {

    @Override
    public boolean equals(Object obj) {
        System.out.println("判断equals");
        return true;
    }

    @Override
    public int hashCode() {
        System.out.println("判断hashcode");
        return 1;
    }


    public static void main(String[] args) {
        Map<ClassA,Object> map = new HashMap<>();
        map.put(new ClassA(), new Object());
        map.put(new ClassA(), new Object());
        System.out.println(map.size());
    }

}