package com.java8.part2.chapter2;

import com.java8.part2.chapter1.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 分组demo
 * @author Mr.zxb
 * @date 2019-03-24 22:07:06
 */
public class GroupingByDemo {

    public static void main(String[] args) {

        List<Dish> menus = new ArrayList<>();

        // 分组 groupingBy分类函数
        // 根据type分类
        Map<Dish.Type, List<Dish>> map = menus.stream().collect(groupingBy(Dish::getType));

        // 根据热量分组
        Map<Dish.CaloricLevel, List<Dish>> disshesByCaloricLevel = menus.stream().collect(groupingBy(GroupingByDemo::getLevel));

        // 多级分组
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishMap = menus.stream().collect(
                // 一级分组
                groupingBy(Dish::getType,
                        // 二级分组
                        groupingBy(GroupingByDemo::getLevel)));

        // 按子组收集数据
        Map<Dish.Type, Long> typesCount = menus.stream().collect(groupingBy(Dish::getType, counting()));
        // 按菜热量最高的类型分组
        Map<Dish.Type, Optional<Dish>> mostCalorircByType = menus.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        // 把收集器的结果转换成另一种类型
        // 查找每个子组中热量最高的Dish   ->  collectingAndThen工厂方法
        Map<Dish.Type, Dish> mostCaloric = menus.stream().collect(
                // 分类函数
                groupingBy(Dish::getType,
                        // 转换函数
                        collectingAndThen(
                                // 包装后的收集器
                                maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        // 与groupingBy联合使用的其他收集器例子
        Map<Dish.Type, Integer> totalCalorirrc = menus.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        // groupingBy与mapping常常一起使用
        // mapping接受两个参数，一个函数对流中元素做变换，另一个则将变换的结果收集起来。
        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelMap = menus.stream().collect(groupingBy(Dish::getType, mapping(GroupingByDemo::getLevel, toSet())));
        // 还可以指定返回Set的类型 -> HashSet, toCollection返回指定的集合类型
        caloricLevelMap = menus.stream().collect(groupingBy(Dish::getType, mapping(GroupingByDemo::getLevel, toCollection(HashSet::new))));
    }

    public static Dish.CaloricLevel getLevel(Dish dish) {
        if (dish.getCalories() <= 400) {
            return Dish.CaloricLevel.DIET;
        } else if (dish.getCalories() <= 700) {
            return Dish.CaloricLevel.NORMAL;
        } else {
            return Dish.CaloricLevel.FAT;
        }
    }
}
