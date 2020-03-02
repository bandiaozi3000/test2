package com.test.bean;

/**
 * @ClassName:TestA
 * @Description:测试A
 * @Author:lm.sun
 * @Date:2019/7/26 13:55
 * @Version:V3.3
 */

public class TestA extends Test{

    private String name;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TestA{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
