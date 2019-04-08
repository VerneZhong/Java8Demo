package com.java8.part2.stream.chapter2;

import com.java8.part2.stream.chapter1.Dish;
import com.java8.part2.stream.chapter3.PrimeNumberCollector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 分区demo
 * 分区是分组的特殊情况
 * @author Mr.zxb
 * @date 2019-03-24 22:08:45
 */
public class PartitioningByDemo {

    public static void main(String[] args) {

        List<Dish> menus = new ArrayList<>();

        // 分区函数
        Map<Boolean, List<Dish>> booleanListMap = menus.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianList = booleanListMap.get(true);
        // 用filter同样可以达到相同的结果
        vegetarianList = menus.stream().filter(d -> d.isVegetarian()).collect(Collectors.toList());

        // 分区的好处保留了分区函数返回true或false的两套元素列表
        Map<Boolean, Map<Dish.Type, List<Dish>>> mapMap = menus.stream().collect(
                // 分区函数
                partitioningBy(Dish::isVegetarian,
                        // 第二个收集器
                        groupingBy(Dish::getType)));

        // 可以找出素食和非素食热量最高的菜
        Map<Boolean, Dish> map = menus.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        // 使用partitioningBy
        menus.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));

        menus.stream().collect(partitioningBy(Dish::isVegetarian, counting()));

        // 将数字按质数和非质数分区
        List<Integer> list = Arrays.asList(2, 4, 5, 6, 9, 10);
        Map<Boolean, List<Integer>> primeMap = list.stream().collect(partitioningBy(PartitioningByDemo::isPrime2));
        primeMap = partitionPrimes(20);
    }

    /**
     * 数字是否为质数
     * @param candidate
     * @return
     */
    public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    /**
     * 数字是否为质数 -> 优化后
     * 仅测试小于等于待测平方根的因子
     * @param candidate
     * @return
     */
    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * 将数字按质数和非质数分区
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(PartitioningByDemo::isPrime2));
    }

    /**
     * 将数字按质数和非质数分区
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> primeNumberCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumberCollector());
    }
}
