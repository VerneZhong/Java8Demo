package com.java8.chapter4;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * 合并操作 BinaryOperator<T>  (T, T) -> T
 * @author Mr.zxb
 * @date 2019-03-01 14:58
 */
public class BinaryOperatorExample {

    public static void main(String[] args) {

        BinaryOperator<Integer> integerBinaryOperator = (i, j) -> i + j;

        // Comparator.naturalOrder() 自然顺序
        BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy(Comparator.naturalOrder());

        System.out.println(integerBinaryOperator.apply(1, 2));
        System.out.println(binaryOperator.apply(1, 3));

        IntBinaryOperator intBinaryOperator = (a, b) -> a * b;
        System.out.println(intBinaryOperator.applyAsInt(2, 2));

        BinaryOperator<String> stringBinaryOperator = (s1, s2) -> s1+s2;
        System.out.println(stringBinaryOperator.apply("ja", "va"));
    }
}
