package jUnit.assertDemo.impl;

import jUnit.assertDemo.IMath;

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
