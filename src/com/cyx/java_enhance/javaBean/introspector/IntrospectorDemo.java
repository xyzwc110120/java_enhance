package com.cyx.java_enhance.javaBean.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

// 内省机制（introspector）：操作 com.cyx.java_enhance.javaBean.introspector.User 类中值的属性
public class IntrospectorDemo {

    public static void main(String[] args) throws IntrospectionException, ReflectiveOperationException {
        // 1：获取 JavaBean 的描述对象
        BeanInfo bean = Introspector.getBeanInfo(User.class);   // 当前方法是获取该类和所有父类的属性
        // 2：获取 JavaBean 中属性的描述器
        PropertyDescriptor[] descriptors = bean.getPropertyDescriptors();

        for (PropertyDescriptor descriptor : descriptors) {
            // 获取当前属性名称
            System.out.println("属性名：" + descriptor.getName());
            /*
            打印后：
                属性名：age
                属性名：class
                属性名：man
                属性名：name

            发现多了一个 class 属性，这是因为 getBeanInfo(Class<?> beanClass) 方法返回的是当前类及其父类的所有属性，
            而 User 类的父类 Object 类中正好有一个 public final Class<?> getClass() 方法，所以获取到一个 class 属性。

            所以我们在这里用另一个方法来获取 JavaBean 的描述对象，
                getBeanInfo(Class<?> beanClass, Class<?> stopClass)：
                    param：
                        beanClass：要获取的 JavaBean 描述对象
                        stopClass：从所在的类停止获取 JavaBean 描述对象

            注意：在Java中，区间都是前闭后开，就是包括前面，不包括后面
            */
        }
        System.out.println("==================================================================================");

        // 1：获取 JavaBean 的描述对象，不包括父类 Object 类
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 2：获取 JavaBean 中属性的描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        User user = User.class.newInstance();
        System.out.println(user);
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            // 获取当前属性名称
            System.out.println("属性名：" + descriptor.getName());
            // 获取当前属性 getter 方法
            System.out.println("getter：" + descriptor.getReadMethod());
            // 获取当前属性 setter 方法
            System.out.println("setter：" + descriptor.getWriteMethod());
            System.out.println("--------------------------------------------");
            if ("name".equals(descriptor.getName())) {
                // 获取属性 getter 方法的对象并通过反射使用该方法
                Method method = descriptor.getWriteMethod();
                method.invoke(user, "超级天才");
            }
        }
        System.out.println(user);
    }

}
