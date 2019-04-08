package com.java8.part1.lambda.chapter4;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * @author Mr.zxb
 * @date 2019-03-01 14:39
 */
public class SupplierExample {

    public static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {

        BooleanSupplier booleanSupplier = () -> true;

        IntSupplier intSupplier = () -> 1024;

        System.out.println(get(() -> "123" + 123));
        System.out.println(booleanSupplier.getAsBoolean());
        System.out.println(intSupplier.getAsInt());
    }
}
