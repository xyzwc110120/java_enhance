package com.cyx.java_enhance.jdbc.daoDemo.domain;

import com.cyx.java_enhance.jdbc.hibernate.annotation.Column;
import com.cyx.java_enhance.jdbc.hibernate.annotation.Table;
import lombok.Getter;
import lombok.Setter;

// 学生对象
@Table("student")
@Getter@Setter
public class Student {

    @Column(required = false)
    private Long id;
    @Column("name")
    private String name;            // 学生名称
    private Integer age;            // 学生年龄

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}