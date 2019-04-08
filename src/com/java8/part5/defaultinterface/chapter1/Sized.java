package com.java8.part5.defaultinterface.chapter1;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:59
 */
public interface Sized {

    int size();

    /**
     * 默认方法
     * @return
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}
