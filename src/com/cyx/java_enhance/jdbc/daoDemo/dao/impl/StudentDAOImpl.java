package com.cyx.java_enhance.jdbc.daoDemo.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.dao.IStudentDAO;
import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void save(Student student) {
        // INSERT INTO student (`name`, age) VALUES ('阿飞', 20)
        String sql = "INSERT INTO student (`name`, age) VALUES ('" + student.getName() + "', " + student.getAge() + ")";
        executeUpdate(sql);
    }

    @Override
    public void delete(Long id) {
        // DELETE FROM student WHERE id = 7
        String sql = "DELETE FROM student WHERE id = " + id;
        executeUpdate(sql);
    }

    @Override
    public void update(Student student) {
        // UPDATE student s SET s.`name` = '沈浪', s.age = 40 WHERE s.id = 7
        String sql = "UPDATE student s SET s.`name` = '" + student.getName() + "', s.age = " + student.getAge()
                + " WHERE s.id = " + student.getId();
        executeUpdate(sql);
    }

    /**
     * 执行 DML 语句
     */
    private void executeUpdate(String sql) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            /*
                assertion (断言)在软件开发中是一种常用的调试方式，很多开发语言中都支持这种机制，如 C，C++ 和 Eiffel 等，
            但是支持的形式不尽相同，有的是通过语言本身、有的是通过库函数等。另外，从理论上来说，通过 assertion 方式可以证明程序的正确性，
            但是这是一项相当复杂的工作，目前还没有太多的实践意义。

                在实现中，assertion 就是在程序中的一条语句，它对一个 boolean 表达式进行检查，一个正确程序必须保证这个 boolean 表达式的值为 true；
            如果该值为 false，说明程序已经处于不正确的状态下，系统将给出警告并且退出。一般来说，assertion 用于保证程序最基本、关键的正确性。
            assertion 检查通常在开发和测试时开启。为了提高性能，在软件发布后，assertion 检查通常是关闭的。下面简单介绍一下 Java 中 assertion 的实现。

                在语法上，为了支持assertion，Java增加了一个关键字assert。它包括两种表达式，分别如下：
                    1. assert expression1;
                    2. assert expression1: expression2;

                expression1表示一个boolean表达式，expression2表示一个基本类型、表达式或者是一个Object，用于在失败时输出错误信息。

                在运行时，如果关闭了assertion功能，这些语句将不起任何作用。如果打开了assertion功能，那么expression1的值将被计算，
            如果它的值为false，该语句强抛出一个AssertionError对象。
            */
            assert connection != null;
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT s.id, s.`name`, s.age FROM student s WHERE s.id = " + id;
        // 先声明资源，为了之后在 finally 代码块中关闭资源
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            // 2、获取连接对象
            connection = JDBCUtil.getConnection();
            // 3、创建语句对象
            assert connection != null;
            statement = connection.createStatement();
            // 4、执行 SQL
            set = statement.executeQuery(sql);
            // 处理结果集
            if (set.next()) {
                return resultSet2Student(set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5、关闭资源
            JDBCUtil.close(connection, statement, set);
        }

        return null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT s.id, s.`name`, s.age FROM student s";
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            // 2、获得连接对象
            connection = JDBCUtil.getConnection();
            // 3、获得语句对象
            assert connection != null;
            statement = connection.createStatement();
            // 4、执行语句
            set = statement.executeQuery(sql);
            // 处理结果集
            while (set.next()) {
                students.add(resultSet2Student(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.close(connection, statement, set);
        }

        return students;
    }

    /**
     * 将结果集转化成 Student 对象
     */
    private Student resultSet2Student(ResultSet set) {
        try {
            return new Student(set.getLong("id"), set.getString("name"), set.getInt("age"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
