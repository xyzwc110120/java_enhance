package com.cyx.java_enhance.javaBean.lombokDemo;

import lombok.Getter;
import lombok.Setter;

// 使用 lombok.jar 工具包简化代码
@Getter
@Setter
public class User {
    // 通过 ctrl + F12 快捷键可以看到类的构造，可以看到有 getter/setter 方法

    private String name;

    private Integer sex;

    private String major;

    /*
        @Data：
            注解在 类 上；提供类所有字段的 get 和 set 方法，此外还提供了 equals、canEqual、hashCode、toString 方法

        @Setter：
            注解在字段上；为单个字段提供 set 方法; 注解在 类 上，为该类所有的字段提供 set 方法，都提供 set 方法

        @Getter：
            注解在字段上；为单个字段提供 get 方法; 注解在 类 上，为该类所有的字段提供 get 方法，都提供 get 方法

        @Slf4j：
            注解在类上；为类提供一个字段名为 log 的 log4j 日志对象

        @AllArgsConstructor：
            注解在类上；为类提供一个全参的构造方法，加了这个注解后，类中不提供默认构造方法了

        @NoArgsConstructor：
            注解在类上；为类提供一个无参的构造方法

        @EqualsAndHashCode：
            注解在类上, 可以生成 equals、canEqual、hashCode 方法

        @NonNull：
        注解在字段上，会自动产生一个关于此参数的非空检查，如果参数为空，则抛出一个空指针异常
    */

}
