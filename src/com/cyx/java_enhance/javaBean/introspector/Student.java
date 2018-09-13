package com.cyx.java_enhance.javaBean.introspector;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Student {
    private String name;
    private Integer sex;
    private Integer age;
    private Date birthday;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
