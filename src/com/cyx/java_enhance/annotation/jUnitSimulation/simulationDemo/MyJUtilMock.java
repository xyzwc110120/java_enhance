package com.cyx.java_enhance.annotation.jUnitSimulation.simulationDemo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// 模仿 JUtil 并赋予注解功能
public class MyJUtilMock {

    /*
        思路：
            1、获取测试类的字节码问对象；
            2、获取测试类中所有方法；
            3、迭代出每一个方法，再判断每个方法上分别标注了什么注解，并归类存储；
            4、循环迭代得到的方法中的测试单元，并执行。
    */

    public static void main(String[] args) throws ReflectiveOperationException {
        // 1、获取测试类的字节码问对象；
        Class<DoTestDemo> clz = DoTestDemo.class;
        DoTestDemo testDemo = clz.newInstance();
        // 2、获取测试类中所有方法；
        Method[] methods = clz.getMethods();
        // 3、迭代出每一个方法，再判断每个方法上分别标注了什么注解，并归类存储；
        List<Method> beforeList = new ArrayList<>();
        List<Method> afterList = new ArrayList<>();
        List<Method> testList = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                afterList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                testList.add(method);
            }
        }
        // 4、循环迭代得到的方法中的测试单元，并执行。
        for (Method test : testList) {
            for (Method before : beforeList) {
                before.invoke(testDemo);
            }
            test.invoke(testDemo);
            for (Method after : afterList) {
                after.invoke(testDemo);
            }
        }
    }

}
