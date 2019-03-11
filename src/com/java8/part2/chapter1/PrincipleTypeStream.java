package com.java8.part2.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 原始类型流特化，IntStream, LongStream, DoubleStream, 从而解决装箱带来的性能问题
 * Stream和特化流直接互转
 * @author Mr.zxb
 * @date 2019-03-10 21:58:41
 */
public class PrincipleTypeStream {

    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();

        // 1.映射到数值流
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();

        // 2.转换回对象流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        // 3.默认值OptionalInt
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();

        // 4.数值范围
        IntStream intStream1 = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        System.out.println(intStream1.count());

        // 5.数值流应用：勾股数  a*a + b*b = c*c
//        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b ) % 1 == 0)
//        .mapToObj(b -> new int [] {a , b, (int) Math.sqrt(a * a + b * b)})).forEach(i -> System.out.println(i[0] + "-" + i[1] + "-" + i[2]));

        // 勾股数第二种方法
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                .filter(d -> d[2] % 1 == 0)).forEach(d -> System.out.println((int) d[2]));


    }
}
