package com.cyx.java_enhance.jdbc.daoDemo.test;

import com.cyx.java_enhance.jdbc.daoDemo.dao.IStudentDAO;
import com.cyx.java_enhance.jdbc.daoDemo.dao.impl.StudentDAOImpl;
import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

// studentDAO 测试类
class StudentDAOTest {

    /*
    JUnit 5 与 JUnit 4 注解的对比：
        JUnit 5	        JUnit 4	        说明
        @Test	        @Test	        被注解的方法是一个测试方法。与 JUnit 4 相同。
        @BeforeAll	    @BeforeClass	被注解的（静态）方法将在当前类中的所有 @Test 方法前执行一次。
        @BeforeEach	    @Before	        被注解的方法将在当前类中的每个 @Test 方法前执行。
        @AfterEach	    @After	        被注解的方法将在当前类中的每个 @Test 方法后执行。
        @AfterAll	    @AfterClass	    被注解的（静态）方法将在当前类中的所有 @Test 方法后执行一次。
        @Disabled	    @Ignore	        被注解的方法不会执行（将被跳过），但会报告为已执行。
    */

    /*
    JUnit 5 中的注解用法：
        @DisplayName：   告诉 JUnit 在报告测试结果时显示 String “Testing using JUnit 5”，而不是测试类的名称。
        @BeforeAll：     告诉 JUnit 在运行这个类中的所有 @Test 方法之前运行 init() 方法一次。
        @AfterAll：      告诉 JUnit 在运行这个类中的所有 @Test 方法之后运行 done() 方法一次。
        @BeforeEach：    告诉 JUnit 在此类中的每个@Test 方法之前运行 setUp() 方法。
        @AfterEach：     告诉 JUnit 在此类中的每个@Test 方法之后运行 tearDown() 方法。
        @Test：          告诉 JUnit，aTest() 方法是一个 JUnit Jupiter 测试方法。
        @Disabled：      告诉 JUnit 不运行此 @Test 方法，因为它已被禁用。
    */

    // 引用 DAO 对象
    // 面向接口编程：接口 变量 = new 实现类();
    // DAO 对象的名字，起名为 xxxDAO
    private IStudentDAO studentDAO = new StudentDAOImpl();

    @Test
    @DisplayName("测试保存学生对象至数据库")
    void save() {
        studentDAO.save(new Student("大头", 50));
    }

    @Test
    @DisplayName("测试删除指定主键值的学生对象")
    void delete() {
        studentDAO.delete(7L);
    }

    @Test
    @DisplayName("测试修改指定主键值的学生对象")
    void update() {
        studentDAO.update(new Student(7L, "沈浪", 40));
    }

    @Test
    @DisplayName("获取指定主键值的学生对象")
    void get() {
        System.out.println(studentDAO.get(2L));
    }

    @Test
    @DisplayName("获取所有学生对象")
    void getAll() {
        List<Student> students = studentDAO.getAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}