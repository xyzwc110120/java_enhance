package com.cyx.java_enhance.jdbc.basic;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DDLTest {

    private final String sql = "CREATE TABLE student (`id` BIGINT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20), `age` INT);";

    /**
     * 创建表
     */
    @Test
    public void testCreateTable() throws Exception {
        // 1、加载注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2、获取连接对象
        Connection connection = DriverManager.getConnection(
                "com.cyx.java_enhance.jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC&useSSL=false", "root", "admin");
        // 3、创建语句对象（Statement 对象）
        Statement statement = connection.createStatement();
        // 4、执行 SQL 语句
        Integer result = statement.executeUpdate(sql);
        // 5、释放资源
        statement.close();
        connection.close();
        System.out.println(result);

        /*
            Connection 接口和 Statement 接口都继承了 AutoCloseable 接口，所以可以自动关闭资源：
                Class.forName("com.mysql.cj.com.cyx.java_enhance.jdbc.Driver");
                try (
                        Connection connection = DriverManager.getConnection(
                                "com.cyx.java_enhance.jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC&useSSL=false", "root", "admin");
                        Statement statement = connection.createStatement()
                ) {
                    statement.executeUpdate(
                            "CREATE TABLE student (`id` BIGINT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20), `age` INT);");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        */
    }


    /**
     * 处理异常
     */
    @Test
    public void testHandleException() {
        // 声明需要关闭的资源
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("com.cyx.java_enhance.jdbc:mysql:///jdbc_demo?serverTimezone=UTC&useSSL=false", "root", "admin");
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
