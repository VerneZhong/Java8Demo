package com.java8.part2.stream.chapter2;

import com.java8.part2.stream.chapter1.Dish;
import com.java8.part2.stream.chapter1.Trader;
import com.java8.part2.stream.chapter1.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 收集器简介
 * @author Mr.zxb
 * @date 2019-03-23 14:52:26
 */
public class CollectDemo {

    public static void main(String[] args) {

        List<Transaction> transactions = Stream.of(new Transaction(new Trader("zxb", "北京"), 1000, 2018)).collect(Collectors.toList());

        // 预定义收集器

        // 归约和汇总
        long count = transactions.stream().collect(counting());
        // 等价于
        count = transactions.stream().collect(reducing(0L, e -> 1L, Long::sum));
        // 等价于
        count = transactions.stream().count();

        List<Dish> menus = new ArrayList<>();

        // 查找流中最大值或最小值
        Comparator<Dish> dishComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menus.stream().collect(Collectors.maxBy(dishComparator));

        // 汇总
        // 总的热量
        int totalCalories = menus.stream().collect(summingInt(Dish::getCalories));
        // 等价于
        totalCalories = menus.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        // 等价于 -> 转换为IntStream流
        totalCalories = menus.stream().mapToInt(Dish::getCalories).sum();

        // 平均数
        double avg = menus.stream().collect(averagingInt(Dish::getCalories));
        // 返回最大值、最小值、平均数的合集
        IntSummaryStatistics summaryStatistics = menus.stream().collect(summarizingInt(Dish::getCalories));
        long count1 = summaryStatistics.getCount();
        long max = summaryStatistics.getMax();
        long sum = summaryStatistics.getSum();
        double avg1 = summaryStatistics.getAverage();

        // 连接字符串 joining工厂方法
        String shortMenu = menus.stream().map(Dish::getName).collect(joining());
        // 等价于
        shortMenu = menus.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();
        // 等价于
        shortMenu = menus.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        // 连接追加，
        shortMenu = menus.stream().map(Dish::getName).collect(joining(", "));
        // 处于性能考虑，尽可能用joining方法

        // 广义的归约汇总
        // reducing定义归约过程
        int total = menus.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        // 用reducing找到热量最高的菜
        Optional<Dish> mostCalorieDish1 = menus.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        // 收集与归约
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        // **错误的使用**
        List<Integer> numbers = stream.reduce(new ArrayList<Integer>(), (List l, Integer e) -> {
            l.add(e);
            return l;
        }, (List l1, List l2) -> {
            l1.addAll(l2);
            return l1;
        });
        // ************

    }

}
