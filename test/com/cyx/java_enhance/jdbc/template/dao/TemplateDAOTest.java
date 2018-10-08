package com.cyx.java_enhance.jdbc.template.dao;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.template.handler.IResultSetHandler;
import com.cyx.java_enhance.jdbc.template.util.JDBCTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        System.out.println(templateDAO.get(4L));
    }

    @Test
    void getAll() {
        System.out.println(templateDAO.getAll());
    }

    @Test
    @DisplayName("获取 student 表总数")
    void getCount() {
        // 通过匿名内部类实现（lambda 表达式）
        System.out.println(JDBCTemplate.query("SELECT COUNT(1) FROM student", (IResultSetHandler<Long>) set -> {
            if (set.next()) {
                return set.getLong(1);
            }
            return 0L;
        }));
    }
}