package com.test.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:StaticDemo
 * @Description:静态方法测试
 * @Author:lm.sun
 * @Date:2019/8/14 17:17
 * @Version:V3.3
 */
public class Test {

    public Test() {
        System.out.print("默认构造方法！--");
    }

    {
        System.out.print("非静态代码块！--");
    }

    static {
        System.out.print("静态代码块！--");
    }

    public static void test() {
        System.out.print("静态方法中的内容! --");
        {
            System.out.print("静态方法中的代码块！--");
        }

    }

    /**
     * List泛型为具体类型,即使为该泛型类型的子类,也是不可以的
     *
     * @param list
     */
    public static void test(List<? extends Test> list) {
        System.out.println(list + "....................");

    }

    public <T extends Number> void test(List<T> dest, List<T> src){

    }


    public static void main(String[] args) {

//        Test test = new Test();
//        System.out.println();
        Test.test();

//        List<TestA> test = new ArrayList<>(); 这个List作为参数是行不通的
        List<Test> test = new ArrayList<>();
        Test.test(test);
    }
}
