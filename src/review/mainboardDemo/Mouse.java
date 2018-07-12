package review.mainboardDemo;

// 鼠标类
public class Mouse implements IUsb {

    @Override
    public void swapData() {
        System.out.println("鼠标接入成功，开始使用");
    }
}
