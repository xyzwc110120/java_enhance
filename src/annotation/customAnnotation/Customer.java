package annotation.customAnnotation;

// 因为 @SuppressWarnings 标签中只有一个属性，切属性名为 value，所以可以省略 value
// @SuppressWarnings(value = "all")
@SuppressWarnings("all")
@VIP(name = "青铜")
public class Customer {

    @Deprecated
    public void doOldWork() {
    }

}
