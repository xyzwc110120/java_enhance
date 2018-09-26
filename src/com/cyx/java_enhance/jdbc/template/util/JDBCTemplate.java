package com.cyx.java_enhance.jdbc.template.util;

import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;
import com.cyx.java_enhance.jdbc.dataSource.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * JDBC 的操作模板
 */
public class JDBCTemplate {

    /**
     * DML 操作（增删改）的模板
     *
     * @param sql       需要执行的，带占位符“?”的 SQL
     * @param params    sql 参数中 ? 占位符对应的参数
     * @return          受影响的行数
     */
    public static int update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DruidUtil.getConnection();
            statement = connection.prepareStatement(sql);
            // 循环设置占位符所设置的参数
            for (int i = 0; i < params.length; i++) {
                // 因为不知道传入的类型，所以设置参数类型为 Object
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
        return 0;
    }

}
