package com.cyx.java_enhance.beanUtils;

/*
    org.apache.commons.beanutils：
        org.apache： 域名倒写；
        commons：    项目名称；
        beanutils：  模块名；
*/

import com.cyx.java_enhance.javaBean.introspector.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 使用 Apache 的 commons_beanutils 组件
public class BeanUtilsDemo {
    /*
        java.lang.ClassNotFoundException: org.apache.commons.logging.LogFactory：
            beanutils 需要依赖 logging 组件
    */

    public static void main(String[] args) throws ReflectiveOperationException {
        Student student = new Student();
        System.out.println(student);

        // 使用1：对对象的属性进行赋值/拷贝
        BeanUtils.setProperty(student, "name", "王大");
        // 除了赋值，还会执行所需的任何类型转换（比如下面 String 转 int）
        BeanUtils.copyProperty(student, "age", "17");
        System.out.println(student);
        System.out.println("----------------------------------------------");


        // 使用2：把一个 com.cyx.java_enhance.javaBean 的属性拷贝到另一个 com.cyx.java_enhance.javaBean 对象中
        Student newStudent = new Student();
        /*
            copyProperties(Object dest, Object orig)：
                在所有属性名称相同的情况下，从原始 bean 复制属性值到目标 bean。
                param：
                    dest：目标 bean 对象
                    orig：被复制的原始 bean 对象
        */
        BeanUtils.copyProperties(newStudent, student);
        System.out.println(newStudent);
        System.out.println("----------------------------------------------");


        // 使用3：把一个 map 集合中的数据拷贝到 com.cyx.java_enhance.javaBean 中
        Map<String, Object> map = new HashMap<>();
        map.put("name", "哇哈哈");
        map.put("age", "");
        map.put("sex", "");
        BeanUtils.copyProperties(newStudent, map);
        System.out.println(newStudent);
        System.out.println("----------------------------------------------");


        // 在使用3中，我们将 map 中的 age 设置为 "" ，赋值到 newStudent 对象中却变成了 0
        // 在此，我们使用转换器工具类 ConvertUtils，这个工具类的职能是在字符串和指定类型的实例之间进行转换
        // 重新注册 Integer 转换器，如果 age 或 sex 没有值，那么此时设置为我们手动设置的默认值 null，而不是缺省的 0
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        BeanUtils.copyProperties(newStudent, map);
        System.out.println(newStudent);
        System.out.println("----------------------------------------------");


        // beanutils 不支持 String --> Date 的转换，需要我们手动设置转换模式
        map.put("birthday", "1992-10-09");
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPattern("YYYY-MM-dd");
        ConvertUtils.register(dateConverter, Date.class);
        BeanUtils.copyProperties(newStudent, map);
        System.out.println(newStudent);
    }

}
