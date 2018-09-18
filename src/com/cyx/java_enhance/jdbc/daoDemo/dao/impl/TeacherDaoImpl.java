package com.cyx.java_enhance.jdbc.daoDemo.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.dao.ITeacherDao;
import com.cyx.java_enhance.jdbc.daoDemo.domain.Teacher;
import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements ITeacherDao {

    @Override
    public void save(Teacher teacher) {
        String sql = "INSERT INTO teacher (`name`, age) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            // 在创建预编译语句对象时传入带‘?’占位符的 SQL
            assert connection != null;
            statement = connection.prepareStatement(sql);
            // 在执行 SQL 语句之前要先为占位符设置值，占位符序号从 1 开始
            statement.setString(1, teacher.getName());
            statement.setInt(2, teacher.getAge());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 因为 Statement 类是 PreparedStatement 的父类，所以可以传入 PreparedStatement 对象
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE teacher t SET t.`name` = ?, t.age = ? WHERE t.id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setInt(2, teacher.getAge());
            statement.setLong(3, teacher.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM teacher WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public Teacher get(Long id) {
        String sql = "SELECT t.id, t.`name`, t.age FROM teacher t WHERE t.id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                return new Teacher(
                        set.getLong("id"), set.getString("name"), set.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT t.id, t.`name`, t.age FROM teacher t";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Teacher> teachers = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            while (set.next()) {
                teachers.add(new Teacher(
                        set.getLong("id"), set.getString("name"), set.getInt("age")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
        return teachers;
    }
}
