package com.java8.part1.chapter2;

import com.java8.part1.chapter1.vo.Apple;

/**
 * 行为参数化
 * @author Mr.zxb
 * @date 2019-01-31 15:42
 */
public interface ApplePredicate {

    boolean test(Apple apple);
}
