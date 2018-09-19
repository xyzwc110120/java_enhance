package com.cyx.java_enhance.jdbc.daoDemo.dao;

public interface ICatchTestDAO {

    /**
     * 通过静态语句对象完成大量插入
     */
    void insertByStatement();

    /**
     * 通过预编译语句对象完成大量插入
     */
    void insertByPrepareStatement();

    /**
     * 通过静态语句对象的批量处理完成大量插入
     */
    void insertByStatementCatch();

    /**
     * 通过预编译语句对象的批量处理完成大量插入
     */
    void insertByPrepareStatementCatch();

}
