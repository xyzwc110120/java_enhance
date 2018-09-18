package com.cyx.java_enhance.jdbc.daoDemo.dao;

import com.cyx.java_enhance.jdbc.daoDemo.dao.impl.MemberDAOImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDAOTest {

    private IMemberDAO memberDao = new MemberDAOImpl();

    @Test
    @DisplayName("通过静态语句登陆，示范 SQL 注入案例，看 SQL 注入是否成功")
    void loginByStatement() {
        // 通过输入 SQL 语句来注入，改变源语句的语义
        memberDao.loginByStatement("' OR 1 = 1 OR '", "ha");
        // 显示登陆成功
    }

    @Test
    @DisplayName("通过预编译语句登陆，示范 SQL 注入案例，看 SQL 注入是否成功")
    void loginByPreparedStatement() {
        memberDao.loginByPreparedStatement("' OR 1 = 1 OR '", "ha");
        // 显示登陆失败（因为预编译语句对象在创建的时候，SQL 的语义就已经确定了，无法改变，这样就防止了 SQL 注入的危险）
    }

    @Test
    @DisplayName("通过事务实现转账案例")
    void testTransfer() {
        memberDao.transfer(1L, 2L, 200);
    }

}