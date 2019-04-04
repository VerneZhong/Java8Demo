package com.java8.part5.chapter3;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:14
 */
public interface B extends A {

    default void hello() {
        System.out.println("Hello from B");
    }
}
