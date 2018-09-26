package com.cyx.java_enhance.jdbc.dataSource;

import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 使用 druid 连接池
public class DruidTest {

    @Test
    @DisplayName("使用 druid 连接池获取数据库连接对象并进行查询")
    void getStudentList() {
        String sql = "SELECT t.id, t.`name`, t.age FROM student t";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = DruidUtil.getConnection();
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            while (set.next()) {
                System.out.println(set.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }

}
