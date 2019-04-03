package com.java8.part4.chapter1;

/**
 * 字母
 * @author Mr.zxb
 * @date 2019-04-03 16:10
 */
public class IsAllLowerCase implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
