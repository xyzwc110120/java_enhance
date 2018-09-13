package com.cyx.java_enhance.jdbc.daoDemo.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

    private static Properties properties = new Properties();

    // 静态代码块：当 JDBCUtil 字节码被加载进 JVM 就立刻执行
    static {
        try {
            // 加载和读取资源文件 db.properties
            // 通过类加载器（ClassLoader）获取文件并转化为流
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            properties.load(stream);
            // 加载 驱动
            Class.forName(properties.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 资源关闭方法（针对于 DML 操作）
     */
    public static void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ignored) {
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ignored) {
            }
        }

    }

    /**
     * 资源关闭方法（针对于 DQL 操作）
     */
    public static void close(Connection connection, Statement statement, ResultSet set) {
        try {
            if (set != null) {
                set.close();
            }
        } catch (Exception ignored) {
        } finally {
            close(connection, statement);
        }
    }

}
