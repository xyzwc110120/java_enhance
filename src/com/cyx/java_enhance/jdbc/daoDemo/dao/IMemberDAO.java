package com.cyx.java_enhance.jdbc.daoDemo.dao;

public interface IMemberDAO {

    /**
     * 登陆（通过静态语句对象）
     */
    void loginByStatement(String account, String password);

    /**
     * 登陆（通过预编译语句对象）
     */
    void loginByPreparedStatement(String account, String password);

    /**
     * 转账
     *
     * @param memberId 转账用户id
     * @param toMemberId 目标用户id
     * @param money 金额
     */
    void transfer(Long memberId, Long toMemberId, Integer money);

}
