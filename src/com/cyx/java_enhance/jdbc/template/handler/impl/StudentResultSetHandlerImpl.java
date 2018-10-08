package com.cyx.java_enhance.jdbc.template.handler.impl;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.template.handler.IResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生对象结果集处理器
 */
public class StudentResultSetHandlerImpl implements IResultSetHandler<List<Student>> {

    /**
     * 将结果集转化为学生对象
     */
    @Override
    public List<Student> handler(ResultSet set) throws SQLException {
        List<Student> list = new ArrayList<>();
        while (set.next()) {
            list.add(new Student(set.getLong("id"), set.getString("name"), set.getInt("age")));
        }
        return list;
    }
}
