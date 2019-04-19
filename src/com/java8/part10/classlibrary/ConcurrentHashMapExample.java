package com.java8.part10.classlibrary;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap类流的操作，ConcurrentHashMap支持三种新的操作
 * forEach -- 对每个键值对进行特定的操作
 * reduce  -- 使用给定的归约函数，将所有的键值对归约出一个新的结果
 * search  -- 对每一个键值对执行一个函数，直到函数的返回值为一个非null值
 *
 * @author Mr.zxb
 * @date 2019-04-19 16:57
 */
public class ConcurrentHashMapExample {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);

        Optional<Integer> maxValue = Optional.of(map.reduceValues(1, Integer::max));
//        System.out.println(maxValue.get());

//        map.forEachKey(1, System.out::println);
        Integer values = map.searchValues(1, i -> i + 2);
//        System.out.println(values);
//        map.forEachValue(1, System.out::println);

        // 返回map长整型long映射的数目
        long l = map.mappingCount();
        System.out.println(l);

        // 集合视图
        ConcurrentHashMap.KeySetView<String, Integer> strings = map.keySet();
        strings.forEach(System.out::println);

        // 创建集合视图
        ConcurrentHashMap.KeySetView<Object, Boolean> objects = ConcurrentHashMap.newKeySet();

    }
}
