package com.java8.part10.classlibrary;

import java.util.Arrays;

/**
 * Arrays新增的静态方法
 *
 * @author Mr.zxb
 * @date 2019-04-19 17:31
 */
public class ArraysExample {

    public static void main(String[] args) {

        int[] array = {6, 7, 1, 2, 3, 4, 5};

        // 以并行的方式对指定的数组进行排序
        Arrays.parallelSort(array);

        System.out.println(Arrays.toString(array));

        int[] array1 = new int[10];
        Arrays.setAll(array1, i -> i * 2);

        System.out.println(Arrays.toString(array1));

        int[] ints = new int[10];
        Arrays.fill(ints, 1);
        // parallelPrefix并发地累积数组中的元素
        Arrays.parallelPrefix(ints, (a, b) -> a + b);
        System.out.println(Arrays.toString(ints));

        boolean b = Boolean.logicalAnd(true, false);
        boolean b1 = Boolean.logicalOr(true, false);
        boolean b2 = Boolean.logicalXor(false, false);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);

        String join = String.join(",", "a", "b", "c");
        System.out.println(join);
    }
}
