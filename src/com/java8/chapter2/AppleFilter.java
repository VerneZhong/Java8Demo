package com.java8.chapter2;

import com.java8.chapter1.vo.Apple;
import com.java8.chapter4.ConsumerExample;
import com.java8.chapter4.LambdaExample1;
import com.java8.chapter4.PredicateExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author Mr.zxb
 * @date 2019-01-31 15:45
 */
public class AppleFilter {

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples2(List<Apple> apples, Predicate<Apple> predicate) {
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
        apples.add(new Apple("green", 150));
        apples.add(new Apple("red", 250));

        apples = filterApples2(apples, a -> a.getWeight() > 200);

        ConsumerExample.foreach(apples, a -> System.out.println(a));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        integers = PredicateExample.filter(integers, i -> i > 2);
        System.out.println(integers);
    }
}
