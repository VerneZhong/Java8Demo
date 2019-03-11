package com.java8.part1.chapter4;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Consumer定义了一个accept抽象方法，它可以接受泛型T的对象，没有返回void。
 * 使用场景：如果需要访问类型T对象，并对其执行某些操作，就可以用这个接口
 * @author Mr.zxb
 * @date 2019-03-01 13:53
 */
public class ConsumerExample {

    public static <T> void foreach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {

        IntConsumer intConsumer = i -> System.out.println(i + 1);

        // LongConsumer
        // DoubleConsumer

        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int i : ints) {
            intConsumer.accept(i);
        }

        BiConsumer<Integer, String> biConsumer = (i, s) -> System.out.println(i + s);
        biConsumer.accept(1, "3");

    }
}
