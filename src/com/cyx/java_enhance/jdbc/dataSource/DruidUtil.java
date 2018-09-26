package com.cyx.java_enhance.jdbc.dataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {

    private static DataSource source = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties"));

            /*
                DruidDataSource的配置是兼容DBCP的。从DBCP迁移到DruidDataSource，只需要修改数据源的实现类就可以了:
                    DBCP的数据库连接池的实现是：
                        org.apache.commons.dbcp.BasicDataSource
                    替换为：
                        com.alibaba.druid.pool.DruidDataSource
            */
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }

}
