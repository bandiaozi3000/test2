package com.test.bean;

/**
 * @ClassName:TestB
 * @Description:测试B
 * @Author:lm.sun
 * @Date:2019/7/26 13:57
 * @Version:V3.3
 */
public class TestB extends Test{

    private String name;

    private String sex;

    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestB{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
