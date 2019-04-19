package com.java8.part10.scala;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Mr.zxb
 * @date 2019-04-18 17:35
 */
public class Foo {

    static int multiply(int x, int y) {
        return x * y;
    }

    /**
     * 函数式
     * @param y
     * @return
     */
    static Function<Integer, Integer> multiply(int y) {
        return x -> x * y;
    }

    public static void main(String[] args) {

//        IntStream.rangeClosed(2, 6).forEach(i -> System.out.printf("Hello %s bottles of beer\n", i));

        Stream<Integer> integerStream2 = Stream.of(1, 3, 5, 7).map(Foo::multiply).map(f -> f.apply(2));

        Stream<Integer> integerStream = Stream.of(1, 3, 5, 7).map(x -> x * 2);

        Stream<Integer> integerStream1 = Stream.of(1, 3, 5, 7).map(multiply(2));

        double d1 = 3.14;
        double d2 = d1;
        Double o1 = d1;
        Double o2 = d2;
        Double ox = o1;
        System.out.println(d1 == d2);
        System.out.println(o1 == o2);
        System.out.println(o1 == ox);
    }
}
