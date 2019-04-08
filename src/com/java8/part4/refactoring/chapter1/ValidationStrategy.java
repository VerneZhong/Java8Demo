package com.java8.part4.refactoring.chapter1;

/**
 * 策略模式的应用
 * @author Mr.zxb
 * @date 2019-04-03 16:10
 */
@FunctionalInterface
public interface ValidationStrategy {

    boolean execute(String s);
}
