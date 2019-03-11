package com.java8.chapter5;

/**
 * 三个参数
 * @author Mr.zxb
 * @date 2019-03-06 14:36
 */
@FunctionalInterface
public interface ThrFunction<T, U, V, R> {

    R apply(T t, U u, V v);

}
