package com.cyx.java_enhance.jdbc.template.handler;


import java.sql.ResultSet;

/**
 * 结果集处理器，使用接口制定一个规范，然后处理结果集的方法都需要按照这个规范实现
 */
public interface IResultSetHandler<T> {

    T handler(ResultSet set) throws Exception;

}
