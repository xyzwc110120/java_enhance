package com.cyx.java_enhance.jdbc.daoDemo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Teacher {

    private Long id;
    private String name;
    private Integer age;

    public Teacher() {
    }

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Teacher(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
