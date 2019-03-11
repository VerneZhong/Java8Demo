package com.java8.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * Predicate接口定义了test方法，它接受一个泛型T对象，并返回boolean值
 * 使用场景：表示一个涉及类型T的布尔表达式时，就可以用这个接口
 *
 * @author Mr.zxb
 * @date 2019-03-01 13:44
 */
public class PredicateExample {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        // 不自动装箱
        IntPredicate intPredicate = i -> i % 2 == 0;

        LongPredicate longPredicate = l -> l % 2 == 0L;

        DoublePredicate doublePredicate = d -> d % 2 == 0.0;

        // 自动装箱， 性能低下
        Predicate<Integer> predicate = i -> i % 2 != 0;

        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int i : ints) {
            if (intPredicate.test(i)) {
                System.out.println(i);
            }
        }

        BiPredicate<Integer, String> biPredicate = (i, s) -> s.length() > i;

        System.out.println(biPredicate.test(2, "123"));


    }

}
