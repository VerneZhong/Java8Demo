package com.java8.part9.patternmatching;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:26
 */
public class Number extends Expr {

    private final int val;

    public Number(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "val: " + val;
    }
}
