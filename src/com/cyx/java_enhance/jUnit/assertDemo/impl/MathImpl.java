package com.cyx.java_enhance.jUnit.assertDemo.impl;

import com.cyx.java_enhance.jUnit.assertDemo.IMath;

public class MathImpl implements IMath {
    @Override
    public int add(int augend, int addend) {
        return augend + addend;
    }

    @Override
    public int divide(int dividend, int divisor) {
        return dividend / divisor;
    }
}
