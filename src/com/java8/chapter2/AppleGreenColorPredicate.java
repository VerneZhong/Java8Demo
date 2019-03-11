package com.java8.chapter2;

import com.java8.chapter1.vo.Apple;

/**
 * 行为策略
 * @author Mr.zxb
 * @date 2019-01-31 15:44
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
