package com.cyx.java_enhance.jdbc.daoDemo.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.dao.IMemberDAO;
import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAOImpl implements IMemberDAO {

    @Override
    public void loginByStatement(String account, String password) {
        String sql = "SELECT m.id, m.account, m.`password` FROM member m WHERE m.account = '" + account + "' AND m.`password` = '" + password +"'";
        // 打印 SQL，可以看出 SQL 注入后的语句被改变了语义：
        // 注入后的 SQL：SELECT m.id, m.account, m.`password` FROM member m WHERE m.account = '' OR 1 = 1 OR '' AND m.`password` = 'ha'
        System.out.println(sql);
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            // 如果返回的结果集有数据，则表示账号密码正确，查得到用户信息，那么结果集的指针也可以向下移动一行
            if (set.next()) {
                System.out.println("登陆成功");
            } else {
                System.out.println("登陆失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }

    @Override
    public void loginByPreparedStatement(String account, String password) {
        String sql = "SELECT m.id, m.account, m.`password` FROM member m WHERE m.account = ? AND m.`password` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            // 当预编译语句对象创建的时候，SQL 语句的语义就确定了，无法改变，这样就避免了 SQL 注入的危险
            statement = connection.prepareStatement(sql);
            statement.setString(1, account);
            statement.setString(2, password);
            set = statement.executeQuery();
            if (set.next()) {
                System.out.println("登陆成功");
            } else {
                System.out.println("登陆失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }

    @Override
    public void transfer(Long memberId, Long toMemberId, Integer money) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            // 设置事务手动提交（取消自动提交）
            connection.setAutoCommit(false);

            // 检查转账用户余额是否足够
            String sql = "SELECT * FROM member m WHERE m.balance >= ? AND m.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, money);
            statement.setLong(2, memberId);
            set = statement.executeQuery();
            if (!set.next()) {
                throw new RuntimeException("用户余额不足！");
            }

            // 减少转账用户金额
            sql = "UPDATE member m SET m.balance = m.balance + ? WHERE m.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, -money);
            statement.setLong(2, memberId);
            statement.executeUpdate();

            // 制造异常，触发回滚
            // int i = 1 / 0;

            // 增加目标用户金额
            statement.setInt(1, money);
            statement.setLong(2, toMemberId);
            statement.executeUpdate();

            // 完成所有逻辑处理，提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();

            // 若出现异常，回滚事务
            /* 必须回滚事务，若不回滚事务，不会释放数据库资源，会造成死锁 */
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (Exception ignored) {
            }
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }
}
