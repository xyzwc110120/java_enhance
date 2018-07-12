package review.mainboardDemo;

// 键盘类
public class Keyboard implements IUsb {

    @Override
    public void swapData() {
        System.out.println("键盘接入成功，开始使用");
    }
}
