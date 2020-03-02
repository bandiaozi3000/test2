package com.test.bean;

/**
 * @ClassName:ClassForName
 * @Description:类加载
 * @Author:lm.sun
 * @Date:2019/8/23 15:47
 * @Version:V3.3
 */
public class ClassForName {

    static{
        System.out.println("静态代码块执行..");
    }

    private static String staticField = staticMethod();

    public static String staticMethod(){
        System.out.println("静态方法执行....");
        return "静态变量";
    }

}
