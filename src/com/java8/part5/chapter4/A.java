package com.java8.part5.chapter4;

/**
 * 菱形继承问题
 * @author Mr.zxb
 * @date 2019-04-04 16:38
 */
public interface A {

    default void hello() {
        System.out.println("hello from a");
    }
}
