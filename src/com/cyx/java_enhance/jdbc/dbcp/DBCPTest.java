package com.cyx.java_enhance.jdbc.dbcp;

import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.*;

// 使用 DBCP 连接池链接数据库
public class DBCPTest {

    /**
     * 来自 commons-dbcp2-2.5.0-src / doc 文件夹中的示例代码：实例化 DataSource 对象
     */
    private DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        /*
            在使用 MySQL 的 6.0.x 以上的 jar 的时候，需要在代码 url 的链接里面指定 serverTimezone（设置时区）。否则就会出现异常：
                    Cannot create PoolableConnectionFactory (The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or
                represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone
                configuration property) to use a more specifc time zone value if you want to utilize time zone support.)

            UTC 代表的是全球标准时间 ，但是我们使用的时间是北京时区也就是东八区，领先 UTC八 个小时。解决方法：
                serverTimezone=Asia/Shanghai
        */
        ds.setUrl("jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("admin");
        return ds;
    }

    @Test
    @DisplayName("使用 DBCP 示例")
    void testGetStudentList() throws Exception {
        String sql = "SELECT t.id, t.`name`, t.age FROM student t";
        /*
            javax.sql.DataSource 接口中有两个方法获取 Connection 对象：
                Connection getConnection()：
                    不需要传入任何参数，参数在创建 DataSource 对象时设置好了；

                Connection getConnection(String username, String password)：
                    需要传入用户名与密码。
                    经测试，若在创建数据源（连接池）对象时不设置用户名及密码，而在获取连接对象时传入，会报不支持该操作异常：
                        java.lang.UnsupportedOperationException: Not supported by BasicDataSource
        */
        // Connection connection = getDataSource().getConnection("root", "admin");
        Connection connection = getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            System.out.println("ID：" + set.getLong("id") + "；NAME：" + set.getString("name") + "；AGE：" + set.getInt("age"));
        }
        set.close();
        statement.close();
        connection.close();
    }

    @Test
    @DisplayName("测试插入数据时的时区问题")
    void testInsertMember() throws Exception {
        String sql = "INSERT INTO member (`account`, `password`, create_time) VALUES (?, ?, ?)";
        Connection connection = getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "1124");
        statement.setString(2, "123");
        /*
            这里系统时间为：2018-09-25 16:28:50，但插入的时间却时：2018-09-25 08:28:50。
            比插入的时间晚了 8 小时，这是因为链接数据库的时候设置的时区的原因
        */
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Test
    @DisplayName("通过 dbcp.properties 配置文件来创建连接池对象")
    void testGetStudent() {
        String sql = "SELECT t.id, t.`name`, t.age FROM student t WHERE t.`id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = DBCPUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, 3);
            set = statement.executeQuery();
            if (set.next()) {
                System.out.println("ID：" + set.getLong("id") + "；NAME：" + set.getString("name") + "；AGE：" + set.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }

}
