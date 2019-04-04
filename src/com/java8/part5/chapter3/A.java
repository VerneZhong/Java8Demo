package com.java8.part5.chapter3;

/**
 * 解决冲突的规则
 * @author Mr.zxb
 * @date 2019-04-04 16:13
 */
public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }
}
