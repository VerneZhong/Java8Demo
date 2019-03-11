package com.java8.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

/**
 * 原始类型特化
 * Function接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。
 * 使用场景：如果需要定义一个Lambda，将输入对象的信息映射到输出，就可以使用这个接口
 * @author Mr.zxb
 * @date 2019-03-01 13:58
 */
public class FunctionExample {

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("lambda", "java", "scala");
        List<Integer> integers = map(strings, s -> s.length());
        System.out.println(integers );

        IntFunction<Integer> intFunction = i -> i * 2;

        IntToDoubleFunction intToDoubleFunction = i -> i * 2;
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int i : ints) {
            System.out.println(intFunction.apply(i));
            System.out.println(intToDoubleFunction.applyAsDouble(i));
        }


    }
}
