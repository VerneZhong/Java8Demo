package com.java8.part4.chapter3;

/**
 * @author Mr.zxb
 * @date 2019-04-03 16:36
 */
@FunctionalInterface
public interface Observer {

    void notify(String text);
}
