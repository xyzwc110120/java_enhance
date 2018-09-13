package com.cyx.java_enhance.annotation.jUnitSimulation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
    @Before：
        当编写测试方法时，经常会发现一些方法在执行前需要创建相同的对象。
        使用 @Before 注解一个 public void 方法会使该方法在每个 @MyTest 注解方法被执行前执行（那么就可以在该方法中创建相同的对象）。
        父类的 @Before 注解方法会在子类的 @Before 注解方法执行前执行。

    @After：
        使用 @After 注解一个 public void 方法会使该方法在每个 @MyTest 注解方法执行后被执行。
        如果在 @Before 注解方法中分配了额外的资源，那么在测试执行完后，需要释放分配的资源，这个释放资源的操作可以在 After 中完成。
        即使在 @Before 注解方法、@MyTest 注解方法中抛出了异常，所有的 @After 注解方法依然会被执行。
        父类中的 @After 注解方法会在子类 @After 注解方法执行后被执行。

    @MyTest：
        @MyTest 注解的 public void 方法将会被当做测试用例，JUnit 每次都会创建一个新的测试实例，然后调用 @MyTest 注解方法，
        任何异常的抛出都会认为测试失败。当以一个类为测试单元时，所有测试用例（测试方法）共属同一个测试实例（具有同一个环境）；
        当以一个方法为测试单元时，JUnit 每次都会创建一个新的测试实例。
        @Test注解提供2个参数：
            1，“expected”，定义测试方法应该抛出的异常，如果测试方法没有抛出异常或者抛出了一个不同的异常，测试失败；
            2，“timeout”，如果测试运行时间长于该定义时间，测试失败（单位为毫秒）。

    @BeforeClass：
        有些时候，一些测试需要共享代价高昂的步骤（如数据库登录），这会破坏测试独立性，通常是需要优化的。
        使用 @BeforeClass 注解一个 public static void 方法，并且该方法不带任何参数，会使该方法在所有测试方法被执行前执行一次，
        并且只执行一次。
        父类的 @BeforeClass 注解方法会在子类的 @BeforeClass 注解方法执行前执行。

    @AfterClass：
        如果在 @BeforeClass 注解方法中分配了代价高昂的额外的资源，那么在测试类中的所有测试方法执行完后，需要释放分配的资源。
        使用 @AfterClass 注解一个 public static void 方法会使该方法在测试类中的所有测试方法执行完后被执行。
        即使在 @BeforeClass 注解方法中抛出了异常，所有的 @AfterClass 注解方法依然会被执行。
        父类中的 @AfterClass 注解方法会在子类 @AfterClass 注解方法执行后被执行。

    @Ignore：
        对包含测试类的类或 @MyTest 注解方法使用 @Ignore 注解将使被注解的类或方法不会被当做测试执行；
        JUnit 执行结果中会报告被忽略的测试数。
*/

// JUtil 的简单使用
public class JUnitSimpleDemo {

    // 如果有多个方法被 @Before 注解标注，则会在每个测试单元执行前先执行这些方法
    @Before
    public void init() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("初始化");
    }

    // 如果有多个方法被 @After 注解标注，则会在每个测试单元执行后再执行这些方法
    @After
    public void destroy() {
        System.out.println("销毁");
        System.out.println("-----------------------------------------------------------------");
    }

    @After
    public void close() {
        System.out.println("关闭");
    }

    @Test
    public void doTest1() {
        System.out.println("测试1");
    }

    @Test
    public void doTest2() {
        System.out.println("测试2");
    }

}
