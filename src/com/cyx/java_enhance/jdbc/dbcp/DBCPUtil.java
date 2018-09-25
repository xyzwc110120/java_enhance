package com.cyx.java_enhance.jdbc.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// DBCP 工具类
public class DBCPUtil {

    private static BasicDataSource source = null;

    static {
        try {
            Properties properties = new Properties();
            // 通过类加载器（ClassLoader）获取 dbcp.properties 文件并转化为流
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties"));
            /*
                public static BasicDataSource createDataSource(final Properties properties):
                    通过 properties 配置文件中的键值来创建 BasicDataSource 对象。
                    注意：
                            properties 中的 key 必须与 BasicDataSource 中的属性名相同，也就是必须与 BasicDataSourceFactory 类中
                        的常量的值相同，因为在此方法中是通过常量值来获取 properties 文件中对应的 key 的值（可查看源码）。
            */
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // 获取连接对象
        return source.getConnection();
    }
}
