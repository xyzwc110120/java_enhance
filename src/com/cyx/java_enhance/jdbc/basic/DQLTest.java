package com.cyx.java_enhance.jdbc.basic;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 执行 DQL 操作
 */
public class DQLTest {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql:///jdbc_demo?serverTimezone=UTC&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    /**
     * 获取学生表总条数
     */
    @Test
    public void testGetMemberTotal() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT COUNT(`id`) total FROM student");
        // 处理结果集
        long total = 0;
        if (set.next()) {
            // 查询表的结果总数要用 long 类型来接收
            // 通过列的序号来获取光标所在行该列的值（列的序号从 1 开始）
            // set.getLong(1);
            // 通过查询出来的列的名称来获取光标所在行该列的值（如果给该列起了别名，就必须通过别名来获取该值）
            total = set.getLong("total");
        }
        System.out.println("student 表总数为：" + total);
        // 关闭资源
        set.close();
        statement.close();
        connection.close();
    }

    /**
     * 通过 id 获取 student 表中指定行的信息
     */
    @Test
    public void testGetStudentById() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT s.`id`, s.name, s.age FROM student s WHERE s.`id` = 2");
        // 处理结果集
        if (set.next()) {
            System.out.println("ID-" + set.getLong("id") + "，NAME-" + set.getString("name") + "，AGE-" + set.getInt("age"));
        }
        // 关闭资源
        set.close();
        statement.close();
        connection.close();
    }

    /**
     * 获取 student 表中的所有数据
     */
    @Test
    public void testGetAllStudent() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT s.`id`, s.name, s.age FROM student s");
        // 处理结果集
        while (set.next()) {
            System.out.println("ID-" + set.getLong("id") + "，NAME-" + set.getString("name") + "，AGE-" + set.getInt("age"));
        }
        // 关闭资源
        set.close();
        statement.close();
        connection.close();
    }

}
