package com.java8.chapter5;

import com.java8.chapter1.vo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用
 * @author Mr.zxb
 * @date 2019-03-06 14:20
 */
public class MethodReferenceExample {

    public static void main(String[] args) {

        // 方法引用
        List<String> str = Arrays.asList("a", "b", "A", "B");
//        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        // 等价于
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);

        Function<String, Integer> stringIntegerFunction = s -> Integer.parseInt(s);
        // 等价于
        stringIntegerFunction = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (list, ele) -> list.contains(ele);
        // 等价于
        contains = List::contains;

        // 构造函数引用
        Supplier<Apple> appleSupplier = Apple::new;
        // 等价于
//        appleSupplier = () -> new Apple();
        Apple a1 = appleSupplier.get();

        Function<Integer, Apple> function = Apple::new;
        a1 = function.apply(110);
        // 等价于
//        function = weight -> new Apple(weight);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        // 等价于
//        apples = map(weights, w -> new Apple(w));
        System.out.println(apples);

        BiFunction<String, Integer, Apple> biFunction = Apple::new;
        // 等价于
//        biFunction = (c, w) -> new Apple(c, w);
        a1 = biFunction.apply("green", 100);
        System.out.println(a1);

        ThrFunction<Integer, Integer, Integer, Color> thrFunction = Color::new;
        // 等价于
//        thrFunction = (r, g, b) -> new Color(r, g, b);
        Color color = thrFunction.apply(12, 34, 55);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> rs = new ArrayList<>();
        for (T t : list) {
            rs.add(function.apply(t));
        }
        return rs;
    }

    static class Color {
        private int r;
        private int g;
        private int b;

        public Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

}
