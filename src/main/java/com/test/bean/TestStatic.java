package com.test.bean;

public class TestStatic {

    public static void replace(String str){
//        str.replace("j","l");
        str ="daa";
        System.out.println(str);
    }

    public static void main(String[] args) {
        String str = "java";
        replace(str);
        System.out.println(str);
    }
}
