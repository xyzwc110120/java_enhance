package com.cyx.java_enhance.jdbc.daoDemo.dao;

import com.cyx.java_enhance.jdbc.daoDemo.dao.impl.CatchTestDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatchTestDAOTest {

    private ICatchTestDAO catchTestDAO = new CatchTestDAOImpl();

    /*
        执行耗时：
            InnoDB：5699 ms
            MyISAM：2086 ms
    */
    @Test
    void insertByStatement() {
        long start = System.currentTimeMillis();
        catchTestDAO.insertByStatement();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /*
        执行耗时：
            InnoDB：6146 ms
            MyISAM：1997 ms
    */
    @Test
    void insertByPrepareStatement() {
        long start = System.currentTimeMillis();
        catchTestDAO.insertByPrepareStatement();
        long end =  System.currentTimeMillis();
        System.out.println(end - start);
    }

    /*
        执行耗时：
            InnoDB：4351 ms
            MyISAM：1667 ms
    */
    @Test
    void insertByStatementCatch() {
        long start = System.currentTimeMillis();
        catchTestDAO.insertByStatementCatch();
        long end =  System.currentTimeMillis();
        System.out.println(end - start);
    }

    /*
        执行耗时：
            InnoDB：600 ms
            MyISAM：349 ms
    */
    @Test
    void insertByPrepareStatementCatch() {
        long start = System.currentTimeMillis();
        catchTestDAO.insertByPrepareStatementCatch();
        long end =  System.currentTimeMillis();
        System.out.println(end - start);
    }

    /*
        从上面的测试可以看出：
            MySQL 服务器既不支持 PrepareStatement 的性能优化，也不支持 JDBC 中的批量操作，但是，在新的 JDBC 驱动中，
        可以通过设置参数 rewriteBatchedStatements 来支持批处理操作（只对 PrepareStatement 的批处理有效，Statement 无效）。
            jdbc:mysql://localhost:3306/jdbc_demo?rewriteBatchedStatements=true
    */
}