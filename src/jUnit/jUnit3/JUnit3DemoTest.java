package jUnit.jUnit3;

import junit.framework.TestCase;

// JUnit 3 测试类
public class JUnit3DemoTest extends TestCase {

    public void testDoWork1() throws Exception {
        System.out.println("开始工作1···");
    }

    public void testDoWork2() {
        System.out.println("开始工作2···");
    }

    // 在 JUnit 3 中，测试方法必须以 test 开头
    public void doWork3() {
        System.out.println("开始工作3···");
    }

    // 覆盖 TestCase 类中的 setUp 方法，会在每个测试单元执行前执行
    @Override
    protected void setUp() throws Exception {
        System.out.println("开始准备···");
    }

    // 覆盖 TestCase 类中的 tearDown 方法，会在每个测试单元执行后执行
    @Override
    public void tearDown() throws Exception {
        System.out.println("结束回收···");
    }
}
