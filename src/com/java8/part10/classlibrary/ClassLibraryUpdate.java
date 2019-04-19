package com.java8.part10.classlibrary;

import java.util.*;

/**
 * 类库的更新
 * @author Mr.zxb
 * @date 2019-04-19 16:30
 */
public class ClassLibraryUpdate {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

//        System.out.println(map.put("a", "a"));

//        System.out.println(map.putIfAbsent("b", "b"));

        // 尝试将key的值和BiFunction里的值合并
//        System.out.println(map.compute("e", (a, b) -> a + b));

        // 当key不存在才会执行Function
//        System.out.println(map.computeIfAbsent("d", a -> a));

        // 当key存在才会执行BiFunction
//        System.out.println(map.computeIfPresent("b", (a, b) -> a + b));
//        System.out.println("------");
//        System.out.println(map.get("a"));
//        System.out.println(map.get("b"));
//        System.out.println(map.get("c"));
//        System.out.println(map.get("d"));
//        System.out.println(map.get("e"));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        // replaceAll会对列表中的每一个元素执行特定的操作，并用处理的结果替换该元素
        numbers.replaceAll(i -> i * 2);
        System.out.println("numbers = " + numbers);


    }
}
