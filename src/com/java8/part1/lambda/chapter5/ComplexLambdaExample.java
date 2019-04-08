package com.java8.part1.lambda.chapter5;

import com.java8.part1.lambda.chapter1.vo.Apple;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * 复合lambda的用法
 * @author Mr.zxb
 * @date 2019-03-06 15:02
 */
public class ComplexLambdaExample {

    public static void main(String[] args) {

        // **比较器复合**
        Comparator<Apple> comparator = comparing(Apple::getWeight);

        // 逆序
        comparator = comparing(Apple::getWeight).reversed();

        // 比较器链，比较苹果的重量以及产地，当两个苹果一样重的时候，比较产地
        comparator = comparing(Apple::getWeight).thenComparing(Apple::getCountry);

        // **谓词复合**
        Predicate<Apple> redApple = a -> a.getColor().equals("red");
        // 得到redApple的非
        Predicate<Apple> notRedApple = redApple.negate();

        // 既是红苹果，又是比较重的苹果
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        // 要么是150以上的红苹果，要么是绿苹果
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150).or(a -> a.getColor().equals("green"));

        // **函数复合**
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        // 先执行前一个函数，再执行后一个
        Function<Integer, Integer> h = f.andThen(g);
        // 4
        System.out.println(h.apply(1));

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.compose(g1);
        // 先执行后一个函数，然后再执行前一个
        // 3
        System.out.println(h1.apply(1));
    }

    /**
     * 信件-实战， 函数复合操作
     */
    static class Letter {

        public static String addHeader(String header) {
            return "From Raoul, Mario and Alan: " + header;
        }

        public static String addFooter(String footer) {
            return footer + " kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }

        public static void main(String[] args) {

            // 先添加抬头，然后进行拼写检查，最后加上落款
            Function<String, String> addHeader = Letter::addHeader;
            Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
            System.out.println(transformationPipeline.apply("labda"));

            // 先加抬头，不检查，直接加上落款
            transformationPipeline = addHeader.andThen(Letter::addFooter);
            System.out.println(transformationPipeline.apply("zxb"));
        }
    }

}
