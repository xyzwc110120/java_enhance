package com.cyx.java_enhance.jdbc.template.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.template.dao.ITemplateDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDAOTest {

    private ITemplateDAO templateDAO = new TemplateDAOImpl();

    @Test
    void save() {
        templateDAO.save(new Student("火云邪神", 55));
    }

    @Test
    void delete() {
        templateDAO.delete(8L);
    }

    @Test
    void update() {
        templateDAO.update(new Student(9L, "沈浪", 41));
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }
}