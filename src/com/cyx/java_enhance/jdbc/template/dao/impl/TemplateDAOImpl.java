package com.cyx.java_enhance.jdbc.template.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.template.dao.ITemplateDAO;
import com.cyx.java_enhance.jdbc.template.util.JDBCTemplate;

import java.util.List;

public class TemplateDAOImpl implements ITemplateDAO {

    @Override
    public void save(Student student) {
        JDBCTemplate.update("INSERT INTO student (`name`, age) VALUES (?, ?)", student.getName(), student.getAge());
    }

    @Override
    public void delete(Long id) {
        JDBCTemplate.update("DELETE FROM student WHERE id = ?", id);
    }

    @Override
    public void update(Student student) {
        JDBCTemplate.update(
                "UPDATE student s SET s.`name` = ?, s.age = ? WHERE s.id = ?",
                student.getName(), student.getAge(), student.getId());
    }

    @Override
    public Student get(Long id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

}
