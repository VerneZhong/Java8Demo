package com.java8.part2.stream.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的使用
 * @author Mr.zxb
 * @date 2019-03-08 20:49:27
 */
public class StreamExample {

    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();

        // 用谓词筛选
        List<Dish> vegetarianMeun = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        // 筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::print);

        // 截断流
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());

        // 跳过元素
        dishes = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());

        // 筛选前2个荤菜
        dishes = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());

        System.out.println();

        // 映射
        List<String> dishNames = dishes.stream().map(Dish::getName).collect(Collectors.toList());

        List<String> words = Arrays.asList("Java8", "Lambda", "In", "Action");
        List<Integer> lengths = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(lengths);

        List<Integer> dishNameLength = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());

        System.out.println("----------------------");

        // 流的扁平化
        words.stream().map(s -> s.split("")).flatMap(Arrays::stream).forEach(StreamExample::print);

        System.out.println("----------------------");
        String[] arrayWords = {"Goodbye", "World"};

        Stream<String> stringStream = Arrays.stream(arrayWords);

        stringStream.forEach(StreamExample::print);

        // 映射案例
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerList = integers.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(integerList);
        System.out.println("----------------------");
        // 案例2
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
        list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList()).forEach(i -> System.out.println(Arrays.toString(i)));
        list1.stream().flatMap(i -> list2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList()).forEach(i -> System.out.println(Arrays.toString(i)));

        // 查找和匹配
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly.");
        }

        // 是否匹配所有元素
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);

        // 是否不匹配 -> 等同于上面
        isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        // 查找元素， findAny返回任意元素
        Optional<Dish> optionalDish = menu.stream().filter(Dish::isVegetarian).findAny();
        // 查找元素，findFirst返回第一个元素
        optionalDish = menu.stream().filter(Dish::isVegetarian).findFirst();
        // 如果存在就执行函数
        optionalDish.ifPresent(StreamExample::print);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> optionalInteger = someNumbers.stream().filter(i -> i % 2 == 0).findAny();
        optionalInteger.ifPresent(StreamExample::print);

        // 归约
        int sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        // 等价于
        sum = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // reduce另一个重载方法，返回Option<T>
        Optional<Integer> optional = someNumbers.stream().reduce((a, b) -> a + b);
        optional.ifPresent(StreamExample::print);

        // 最大值和最小值
        Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
        max.ifPresent(StreamExample::print);
        Optional<Integer> min = someNumbers.stream().reduce(Integer::min);
        min.ifPresent(StreamExample::print);

        // 用map和reduce来统计流中有多少个菜
        int menuCount = menu.stream().map(d -> 1).reduce(0, Integer::sum);
        // 等价于
        long count = menu.stream().count();
    }

    public static <T> void print(T t) {
        System.out.println(t);
    }
}
