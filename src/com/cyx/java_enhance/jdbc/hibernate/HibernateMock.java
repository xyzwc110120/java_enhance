package com.cyx.java_enhance.jdbc.hibernate;

import com.cyx.java_enhance.jdbc.hibernate.annotation.Column;
import com.cyx.java_enhance.jdbc.hibernate.annotation.Table;
import com.cyx.java_enhance.jdbc.template.util.JDBCTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HibernateMock {

    public static <T> void save(T t) {
        try {
            // 获取对象的类对象
            Class tClass = t.getClass();
            String tableName;
            // 获取类对象上是否存在 Table 注解，如果存在，获取注解的 value 值作为表名，否则用类名的全小写作为表面
            Table table = (Table) tClass.getAnnotation(Table.class);
            if (table == null) {
                tableName = tClass.getSimpleName().toLowerCase();
            } else {
                tableName = table.value();
            }

            // INSERT INTO student (`name`, age) VALUES (?, ?)
            // 拼接 SQL
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(tableName).append(" (");

            // 获取 t 类中的所有字段对象
            Field[] fields = tClass.getDeclaredFields();
            // 拼接表中的列名
            StringBuilder columnSql = new StringBuilder();
            // 拼接占位符
            StringBuilder placeholderSql = new StringBuilder();
            // 存储占位符对应的参数值
            List<Object> paramList = new ArrayList<>();
            for (Field field : fields) {
                // 获取字段上的 Column 注解
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    if (!column.required()) {
                        continue;
                    }
                    // 获取注解的 value 值作为列名
                    columnSql.append(column.value()).append(",");
                } else {
                    // 获取字段名作为列名
                    columnSql.append(field.getName()).append(",");
                }
                placeholderSql.append("?,");
                // 通过反射获取字段的值:
                // 通过反射操作类的私有（private）成员变量时，需要通过field.setAccessible(true)将字段设置为可以访问的:
                field.setAccessible(true);
                paramList.add(field.get(t));
                /*
                    通过 org.apache.commons.beanutils 包中的 PropertyUtils 类中的 getSimpleProperty 方法直接获取指定属性值:
                        paramList.add(PropertyUtils.getSimpleProperty(t, field.getName()));
                */
            }
            // 删除拼接中的最后一个“,”
            columnSql.deleteCharAt(columnSql.length() - 1);
            placeholderSql.deleteCharAt(placeholderSql.length() - 1);
            // 将 SQL 拼接完成
            sql.append(columnSql).append(") VALUES (").append(placeholderSql).append(")");
            System.out.println(sql);

            // 执行 SQL
            JDBCTemplate.update(sql.toString(), paramList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
