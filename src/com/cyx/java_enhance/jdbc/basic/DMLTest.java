package com.cyx.java_enhance.jdbc.basic;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 执行 DML 操作
 */
public class DMLTest {

    private final String DRIVER = "com.mysql.cj.com.cyx.java_enhance.jdbc.Driver";
    private final String URL = "com.cyx.java_enhance.jdbc:mysql:///jdbc_demo?serverTimezone=UTC&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    /*
        1、加载注册驱动
        2、获取连接对象
        3、创建语句对象（Statement 对象）
        4、执行 SQL 语句
        5、释放资源
    */

    /**
     * 向 student 表插入数据
     */
    @Test
    public void testInsertStudent() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("INSERT INTO student (`name`, `age`) VALUES ('来福', 18)");
        statement.close();
        connection.close();
        if (result > 0) {
            System.out.println("插入成功");
        }
    }

    /**
     * 修改 student 表中数据
     */
    @Test
    public void testUpdateStudent() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("UPDATE student s SET s.`name` = '黑虎', s.age = 19 WHERE s.`id` = 2");
        statement.close();
        connection.close();
        if (result > 0) {
            System.out.println("修改成功");
        }
    }

    /**
     * 删除 student 表中数据
     */
    @Test
    public void testDeleteStudent() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("DELETE FROM student WHERE `id` = 2");
        statement.close();
        connection.close();
        if (result > 0) {
            System.out.println("删除成功");
        }
    }

}
