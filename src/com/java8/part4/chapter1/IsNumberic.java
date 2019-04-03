package com.java8.part4.chapter1;

/**
 * 数字
 * @author Mr.zxb
 * @date 2019-04-03 16:11
 */
public class IsNumberic implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
