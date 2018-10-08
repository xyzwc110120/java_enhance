package com.cyx.java_enhance.jdbc.template.util;

import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;
import com.cyx.java_enhance.jdbc.dataSource.DruidUtil;
import com.cyx.java_enhance.jdbc.template.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC 的操作模板
 * 将重复的操作封装到一起（对 com.cyx.java_enhance.jdbc.daoDemo.dao.impl.StudentDAOImpl 类的封装重构）
 */
public class JDBCTemplate {

    /**
     * DQL 查询）操作的模板
     *
     * @param sql       需要执行的，带占位符“?”的 DQL 操作 SQL
     * @param handler   结果集处理器，用来做结果集转换为对应对象的操作
     * @param params    sql 参数中 ? 占位符对应的参数
     * @param <T>       需要返回的对象类型
     * @return          查询出来的对象集合（就算是单个对象，也可以放在集合中）
     */
    public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = DruidUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return handler.handler(statement.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
        throw new RuntimeException("查询操作执行错误");
    }

    /**
     * DML（增删改）操作的模板
     *
     * @param sql       需要执行的，带占位符“?”的 DML 操作 SQL
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
