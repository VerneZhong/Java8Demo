package com.java8.part1.lambda.chapter1;

import com.java8.part1.lambda.chapter1.vo.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 传递代码
 * @author Mr.zxb
 * @date 2019-01-31 14:20
 */
public class Example2 {

    /**
     * old java 处理方式
     * @param apples
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * java8的处理方式
     * @param apples
     * @param predicate
     * @return
     */
    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>();

        filterApples(apples, Apple::isGreenApple);

        filterApples(apples, a -> "green".equals(a.getColor()));

    }
}
