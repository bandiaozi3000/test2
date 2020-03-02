package com.test.bean;

/**
 * @ClassName:ExceptionDemo
 * @Description:异常Demo
 * @Author:lm.sun
 * @Date:2019/8/14 15:29
 * @Version:V3.3
 */
public class ExceptionDemo {

    public double devide(int a,int b){
        int c = 3;
        try{
          return c;
        }finally {
            c=5;
        }
    }
}
