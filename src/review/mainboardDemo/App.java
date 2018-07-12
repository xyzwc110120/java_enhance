package review.mainboardDemo;

// 测试类
public class App {

    public static void main(String[] args) {
        // 鼠标对象
        Mouse mouse = new Mouse();
        // 键盘对象
        Keyboard keyboard = new Keyboard();

        // 接入 USB 设备
        Mainboard.MAINBOARD.installUSB("牧马人鼠标", mouse);
        Mainboard.MAINBOARD.installUSB("罗技键盘", keyboard);
        // 因为有些设备只使用一次，使用匿名内部类
        Mainboard.MAINBOARD.installUSB("打印机", () -> System.out.println("打印机正在打印"));

        // 主板开始工作
        Mainboard.MAINBOARD.doWork();

        // 拔出鼠标设备
        Mainboard.MAINBOARD.removeUsbPlugins(mouse);
        System.out.println("-------------------------------");

        // 直接从配置文件中获取设备
        NewMainboard.MAINBOARD.doWork();
    }

}
