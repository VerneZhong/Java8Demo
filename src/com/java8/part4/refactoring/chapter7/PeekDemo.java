package com.java8.part4.refactoring.chapter7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * peek的使用，在流的每个元素恢复运行之前，插入执行一个动作
 * @author Mr.zxb
 * @date 2019-04-04 15:44
 */
public class PeekDemo {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3);

        integers.stream().peek(i -> System.out.println("from stream " + i))
                .map(i -> i + 1).peek(i -> System.out.println("after map +  " + i))
                .map(i -> i * 2).peek(i -> System.out.println("after map * " + i)).collect(Collectors.toList());
    }
}
