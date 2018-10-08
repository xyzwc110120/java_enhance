package com.cyx.java_enhance.jdbc.template.dao;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.template.handler.impl.BeanHandler;
import com.cyx.java_enhance.jdbc.template.handler.impl.BeanListHandler;
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
        // List<Student> list = JDBCTemplate.query("SELECT s.id, s.`name`, s.age FROM student s WHERE s.id = ?",
        //         new StudentResultSetHandlerImpl(), id);
        // return list.size() == 1 ? list.get(0) : null;

        return JDBCTemplate.query("SELECT s.id, s.`name`, s.age FROM student s WHERE s.id = ?",
                new BeanHandler<>(Student.class), id);
    }

    @Override
    public List<Student> getAll() {
        // return JDBCTemplate.query("SELECT s.id, s.`name`, s.age FROM student s", new StudentResultSetHandlerImpl());

        return JDBCTemplate.query("SELECT s.id, s.`name`, s.age FROM student s", new BeanListHandler<>(Student.class));
    }

}
