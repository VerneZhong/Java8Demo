package com.java8.part9.functional;

import java.util.function.Predicate;

/**
 * 基本的链接列表
 * @author Mr.zxb
 * @date 2019-04-18 11:20
 */
public interface MyList<T> {

    T head();

    MyList<T> tail();

    MyList<T> filter(Predicate<T> predicate);

    default boolean isEmpty() {
        return true;
    }
}
