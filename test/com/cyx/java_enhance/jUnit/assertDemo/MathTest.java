package com.cyx.java_enhance.jUnit.assertDemo;

import com.cyx.java_enhance.jUnit.assertDemo.impl.MathImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


// 根据接口 IMath 生成测试类
public class MathTest {

    // 依赖关系
    private IMath math = new MathImpl();

    @Test
    public void add() {
        int ret = math.add(1, 2);
        assertEquals(3, ret);
    }

    @Test(expected = ArithmeticException.class)
    public void divide() {
        int ret = math.divide(9, 0);
        assertEquals("答案不一致",  1, ret);
    }
}