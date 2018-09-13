package com.cyx.java_enhance.annotation;

import java.util.Set;

// JDK 中的内置注解
public class JdkAnnotationDemo {

    // 限制覆写父类方法
    @Override
    public String toString() {
        return super.toString();
    }


    // 标记为已过时的，废弃的，不推荐的
    @Deprecated
    public void doOldWork1() {
    }
    // 注解是在 JDK 1.5 才出现的，但是在这之前也有过时方法，在使用注解之前 Java 使用文档注释来标记过时的
    /**
     * @deprecated
     */
    public void doOldWork2() {
    }
    public void doOldWork() {
        doOldWork1();
        doOldWork2();
    }


    // 抑制编译器发出的警告（注解后需要加上参数，为需要抑制的警告类型）
    @SuppressWarnings("deprecation")
    public void doWork() {
        doOldWork1();
        doOldWork2();
    }
    // 抑制多个警告类型可以使用数组传递
    // @SuppressWarnings("all")：消除所有类型警告，也可以写作：@SuppressWarnings(value = "all")
    @SuppressWarnings({"deprecation", "unused"})
    public void suppressWarnings() {
        doOldWork1();
        Set set = null;
    }


}
