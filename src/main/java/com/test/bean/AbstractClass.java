package com.test.bean;

public class AbstractClass {

    public static class A{
        private  String a = "a";
    }


    public static void main(String[] args) {
        A a = new AbstractClass.A();
        System.out.println(a.a);
    }

}


