package com.java8.part9.patternmatching;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:36
 */
@FunctionalInterface
public interface ThrFunction<S, T, U, R> {

    R apply(S s, T t, U u);
}
