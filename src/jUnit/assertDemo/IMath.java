package jUnit.assertDemo;

// 计算接口
public interface IMath {

    /* 注释文档写在接口中 */

    /**
     * 加法运算
     *
     * @param augend 被加数
     * @param addend 加数
     * @return 和
     */
    int add(int augend, int addend);

    /**
     * 两数之商（只考虑整除）
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商
     */
    int divide(int dividend, int divisor);

}
