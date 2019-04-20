package com.java8.part10.stream;

import com.java8.part2.stream.chapter1.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.zxb
 * @date 2019-04-20 22:07:29
 */
public class Main {

    public static void main(String[] args) {

        List<Dish> list = new ArrayList<>();

        list.add(new Dish("红烧鱼", false, 200, Dish.Type.FISH));
        list.add(new Dish("红烧肉", false, 300, Dish.Type.MEAT));
        list.add(new Dish("毛豆", true, 100, Dish.Type.OTHER));


        Stream<Dish> menuStream = list.stream();

        Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(Collectors.joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get())
                .fork("dishesByType", s -> s.collect(Collectors.groupingBy(Dish::getType)))
                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println(shortMenu);
        System.out.println(totalCalories);
        System.out.println(mostCaloricDish);
        System.out.println(dishesByType);
    }
}
