package com.java8.part1.chapter5;

import com.java8.part1.chapter1.vo.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * lambda实战, 排序的写法进化
 * @author Mr.zxb
 * @date 2019-03-06 14:53
 */
public class LambdaActualCombat {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        // 第一步：传递代码，用类来传递行为
        apples.sort(new AppleComparator());

        // 第二步：使用匿名类
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        // 第三步：使用lambda表达式
        apples.sort(((o1, o2) -> o1.getWeight() - o2.getWeight()));
        apples.sort(comparing(a -> a.getWeight()));

        // 第四步：使用方法引用
        apples.sort(comparing(Apple::getWeight));
    }

    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}
