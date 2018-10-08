package com.cyx.java_enhance.jdbc.template.handler.impl;

import com.cyx.java_enhance.jdbc.template.handler.IResultSetHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 把结果集中的都行数据，封装成一个对象的集合
 *
 * @param <T> 用户需要的对象的类型对象
 */
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

    private Class<T> classType;


    public BeanListHandler(Class<T> classType) {
        this.classType = classType;
    }


    @Override
    public List<T> handler(ResultSet set) throws Exception {
        List<T> list = new ArrayList<>();

        while (set.next()) {
            T t = classType.newInstance();
            // 这里先将对象添入集合，之后对对象做操作（引用传递）
            list.add(t);
            BeanInfo info = Introspector.getBeanInfo(t.getClass(), Object.class);
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                propertyDescriptor.getWriteMethod().invoke(t, set.getObject(propertyDescriptor.getName()));
            }
        }

        return list;
    }
}
