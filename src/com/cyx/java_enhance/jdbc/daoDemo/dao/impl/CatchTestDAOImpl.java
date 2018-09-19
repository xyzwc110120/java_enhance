package com.cyx.java_enhance.jdbc.daoDemo.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.dao.ICatchTestDAO;
import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CatchTestDAOImpl implements ICatchTestDAO {

    @Override
    public void insertByStatement() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.createStatement();
            for (int i = 0; i < 100; i++) {
                String sql = "INSERT INTO batch_test (`value`) VALUES (" + i + ")";
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public void insertByPrepareStatement() {
        String sql = "INSERT INTO batch_test (`value`) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                statement.setInt(1, i);
                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public void insertByStatementCatch() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.createStatement();
            for (int i = 0; i < 100; i++) {
                String sql = "INSERT INTO batch_test (`value`) VALUES (" + i + ")";
                statement.addBatch(sql);
                if (i % 20 == 0) {
                    // 执行批量处理
                    statement.executeBatch();
                    // 清理掉之前存入的 SQL
                    statement.clearBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Override
    public void insertByPrepareStatementCatch() {
        String sql = "INSERT INTO batch_test (`value`) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                statement.setInt(1, i);
                statement.addBatch();
                if (i % 20 == 0) {
                    // 执行批量处理
                    statement.executeBatch();
                    // 清理掉之前存入的 SQL
                    statement.clearBatch();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

}
