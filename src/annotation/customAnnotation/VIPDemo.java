package annotation.customAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// 第三方程序，来赋予注解功能
public class VIPDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        // 需求：获取 Customer 类上所有的注解
        // 1：获取 Customer 的字节码对象
        Class<Customer> clz = Customer.class;
        // 2：获取类上的所有注释
        Annotation[] annotations = clz.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println(annotation);
        }
        /*
            在这里我们获取到了一个注解对象：@annotation.customAnnotation.VIP(level=1, name=青铜)
            但是在 Customer 类上我们标注了 @SuppressWarnings 和 @VIP 两个注解，
            这是因为 @SuppressWarnings 注解只保存在源文件中，一旦编译后就消失了，也就不会存在在 JVM 中， 我们也就无法通过反射获取该注解对象。
            也就是说，可以通过反射获取的注解对象，它必须是要可以保存至 JVM 的， 也就是 @Retention 标签的属性必须为 RetentionPolicy.RUNTIME。
        */
        System.out.println("-----------------------------------------------------------------");


        // 需求：判断 Customer 上是否有 @VIP 注释
        if (clz.isAnnotationPresent(VIP.class)) {
            System.out.println("有");
            // 获取指定注解类型的注解对象（若没有该注解，则返回 null，也可以用此方法做是否存在指定类型注解的判断）
            VIP annotation = clz.getAnnotation(VIP.class);
            System.out.println(annotation.name());
            System.out.println(annotation.level());
        } else {
            System.out.println("没有");
        }
        System.out.println("-----------------------------------------------------------------");


        // 需求：获取 Customer 类中 doOldWork 方法上的注解
        // 获取 doOldWork 方法的 Method 对象
        Method method = clz.getMethod("doOldWork");
        Annotation[] as = method.getDeclaredAnnotations();
        for (Annotation a : as) {
            System.out.println(a);
        }
    }
}
