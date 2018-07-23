package annotation.jUnitSimulation.simulationDemo;

// 模拟测试类
public class DoTestDemo {

    @MyBefore
    public void init() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("初始化");
    }

    @MyAfter
    public void destroy() {
        System.out.println("销毁");
    }

    @MyAfter
    public void close() {
        System.out.println("关闭");
    }

    @MyTest
    public void doTest1() {
        System.out.println("测试1");
    }

    @MyTest
    public void doTest2() {
        System.out.println("测试2");
    }

}
