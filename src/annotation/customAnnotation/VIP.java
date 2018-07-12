package annotation.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface VIP {

    String name();

    // 注解的属性的类型只能是：基本类型、String、Class、annotation、枚举，以及以上类型的数组
    // Integer level();

    int level();

}
