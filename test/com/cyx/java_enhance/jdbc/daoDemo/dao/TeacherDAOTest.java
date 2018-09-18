package com.cyx.java_enhance.jdbc.daoDemo.dao;

import com.cyx.java_enhance.jdbc.daoDemo.dao.impl.TeacherDaoImpl;
import com.cyx.java_enhance.jdbc.daoDemo.domain.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TeacherDAOTest {

    private ITeacherDao teacherDao = new TeacherDaoImpl();

    @Test
    @DisplayName("保存老师信息")
    void save() {
        Teacher teacher = new Teacher("萧峰", 30);
        teacherDao.save(teacher);
    }

    @Test
    @DisplayName("修改老师信息")
    void update() {
        Teacher teacher = new Teacher(5L, "马大哈", 35);
        teacherDao.update(teacher);
    }

    @Test
    @DisplayName("删除老师信息")
    void delete() {
        teacherDao.delete(5L);
    }

    @Test
    @DisplayName("获取指定老师信息")
    void get() {
        Teacher teacher = teacherDao.get(4L);
        System.out.println(teacher);
    }

    @Test
    @DisplayName("获取所有老师信息")
    void getAll() {
        List<Teacher> teachers = teacherDao.getAll();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}