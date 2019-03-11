package com.java8.chapter4;

import java.util.function.UnaryOperator;

/**
 * UnaryOperator<T>  T -> T
 * @author Mr.zxb
 * @date 2019-03-01 14:54
 */
public class UnaryOperatorExample {

    public static void main(String[] args) {


        UnaryOperator<String> stringUnaryOperator = String::toUpperCase;
        System.out.println(stringUnaryOperator.apply("java"));

        System.out.println(UnaryOperator.identity().apply(1));
        System.out.println(UnaryOperator.identity().apply(false));
        System.out.println(UnaryOperator.identity().apply("str"));

    }
}
