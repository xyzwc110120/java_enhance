package com.cyx.java_enhance.jdbc.basic;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

    /**
     * 获取数据库连接对象
     */
    @Test
    public void testConnection() throws Exception {
        // 1、加载注册驱动
        /*
            在 mysql-connector-java-8.X.XX 版本中的驱动类全限定名为：com.mysql.cj.com.cyx.java_enhance.jdbc.Driver，
            而在 mysql-connector-java-8.X.XX 版本之前的全限定名为：com.mysql.com.cyx.java_enhance.jdbc.Driver。
        */
        /*
            完成加载和注册驱动的原理：
                1、把 com.mysql.cj.com.cyx.java_enhance.jdbc.Driver 字节码加载进 JVM
                2、当一份字节码加载进 JVM 时，就会执行该字节码中的静态代码块。

                MySQL 驱动实现类 Driver 源码：
                    package com.mysql.cj.com.cyx.java_enhance.jdbc;

                    import java.sql.*;
                    import java.sql.SQLException;

                    public class Driver extends NonRegisteringDriver implements java.sql.Driver {
                        public Driver() throws SQLException {
                        }

                        // 当该类被加载进 JVM 时，就会执行该静态代码块，就会注册驱动
                        static {
                            try {
                                DriverManager.registerDriver(new Driver());
                            } catch (SQLException var1) {
                                throw new RuntimeException("Can't register driver!");
                            }
                        }
                    }
        */
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2、获取连接
        Connection connection = DriverManager.getConnection(
                "com.cyx.java_enhance.jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC&useSSL=false", "root", "admin");
        // 若连接的是本机的 MySQL ，并且端口时默认的 3306，则可以简写
        Connection autoConnection = DriverManager.getConnection(
                "com.cyx.java_enhance.jdbc:mysql:///jdbc_demo?serverTimezone=UTC&useSSL=false", "root", "admin");
        /*
            使用连接mysql的jdbc驱动最新版引发的问题：
                在 com.cyx.java_enhance.jdbc:mysql://localhost:3306/banma 后面加上参数：
                    com.cyx.java_enhance.jdbc:mysql://localhost:3306/banma?serverTimezone=UTC&useSSL=false
        */
    }

}
