package com.java8.part2.stream.chapter1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建流，由值、数组、文件、函数生成流
 *
 * @author Mr.zxb
 * @date 2019-03-11 21:37:39
 */
public class BuildStream {

    public static void main(String[] args) throws IOException {

        // 1.由值生成流
        Stream<String> stringStream = Stream.of("Java8", "Lambda", "Stream");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        // 2.由数组生成流
        int[] array = {1, 3, 5, 7, 9};
        IntStream intStream = Arrays.stream(array);
        int sum = intStream.sum();
        System.out.println(sum);

        // 3.由文件生成流
        Stream<String> fileStream = Files.lines(Paths.get("E:\\java工具\\esetNod32.txt"), Charset.forName("GBK"));
        long uniqueWords = fileStream.flatMap(l -> Arrays.stream(l.split(""))).distinct().count();
        System.out.println(uniqueWords);

        // 4-1.由函数生成流-iterate
        Stream.iterate(0, a -> a + 2).limit(5).forEach(System.out::println);

        System.out.println("---------------------");

        // 生成斐波那契数列，前两个数的和等于第三个数，用iterate生成前20个数列
//        Stream.iterate(new int[]{0, 1}, i ->  new int[] {i[1], i[0] + i[1]}).limit(20).forEach(t -> System.out.println(t[0] + "-" + t[1]));
        Stream.iterate(new int[]{0, 1}, i ->  new int[] {i[1], i[0] + i[1]}).limit(10).map(t -> t[0]).forEach(System.out::println);

        // 4-2.由函数生成流-generate
        DoubleStream.generate(Math::random).limit(5).forEach(System.out::println);

    }
}

