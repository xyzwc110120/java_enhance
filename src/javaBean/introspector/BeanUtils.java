package javaBean.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

// Map 和 JavaBean 之间的互相转换
public class BeanUtils {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setName("大大");
        student.setSex(0);
        student.setAge(18);

        Map<String, Object> map = bean2Map(student);
        System.out.println(map);
        System.out.println(map2Bean(map, Student.class));

    }

    /**
     * 将 JavaBean 对象转换为 Map 对象
     */
    private static Map<String, Object> bean2Map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 1：获取 bean 对象的描述对象
        BeanInfo info = Introspector.getBeanInfo(bean.getClass(), Object.class);
        // 2：获取描述对象的属性描述器
        PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
        // 3：遍历属性描述器，获取属性值并传入 map 对象中
        for (PropertyDescriptor descriptor : descriptors) {
            map.put(descriptor.getName(), descriptor.getReadMethod().invoke(bean));
        }
        return map;
    }


    /**
     * 将 map 对象 转换为 JavaBean 对象
     *
     * @param beanType 需要转换的 JavaBean 对象的类型
     * @param <T> 表示使用 T 来表示一种类型
     */
    private static <T> T map2Bean(Map<String, Object> map, Class<T> beanType) throws Exception {
        BeanInfo info = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
        // 通过反射实例化一个 bean 对象
        T t = beanType.newInstance();
        for (PropertyDescriptor descriptor: descriptors) {
            descriptor.getWriteMethod().invoke(t, map.get(descriptor.getName()));
        }
        return t;
    }

}
