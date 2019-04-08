package com.java8.part5.defaultinterface.chapter3;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:32
 */
public interface F {

    default void hello() {
        System.out.println("Hello from F");
    }
}
