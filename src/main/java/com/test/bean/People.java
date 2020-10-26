package com.test.bean;

public class People implements Cloneable{
    private static People people = new People();
    private Integer id;
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public People(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public People() {
    }

    public static People getInstance(){
        return people;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
