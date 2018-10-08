package com.cyx.java_enhance.jdbc.template.handler.impl;

import com.cyx.java_enhance.jdbc.template.handler.IResultSetHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * 把结果集中的一行数据，封装成一个对象，对象类型由调用此方法的人决定（使用内省机制）
 * 规定：
 *      1、查询出来的列明必须和对象中的属性名相同
 *      2、查询出来的列的类型必须和 Java 中的类型相匹配
 *
 * （按照之前 StudentResultSetHandlerImpl 类的写法，不同类型的对象需要写不同的处理器，太繁琐）
 *
 * @param <T> 用户需要转换的对象
 */
public class BeanHandler<T> implements IResultSetHandler<T> {

    /**
     * 需要转换的对象的类型对象
     */
    private Class<T> classType;

    /**
     * 构造函数，设置需要转换的对象的类型对象
     *
     * @param classType 对象的类型对象
     */
    public BeanHandler(Class<T> classType) {
        this.classType = classType;
    }


    @Override
    public T handler(ResultSet set) throws Exception {
        T t = classType.newInstance();

        // 内省机制
        // 获取 JavaBean 的描述对象，不包括父类 Object 类
        BeanInfo info = Introspector.getBeanInfo(t.getClass(), Object.class);
        // 获取 JavaBean 中属性的描述器
        PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        if (set.next()) {
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获取属性名称，并通过属性名称获取对应列名的数据设置为该属性值
                propertyDescriptor.getWriteMethod().invoke(t, set.getObject(propertyDescriptor.getName()));
            }
        }

        return t;
    }
}
