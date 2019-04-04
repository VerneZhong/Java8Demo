package com.java8.part4.chapter5;

import java.util.function.Supplier;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:19
 */
public class Main {

    public static void main(String[] args) {

        // 原始方式
        Product product = ProductFactory.createProduct("loan");

        // 使用lambda方式
        Supplier<Product> loanSupplier = Loan::new;

        Product loan = loanSupplier.get();

        // java8方式
        Product product2 = ProductFactory.createProduct2("bond");
    }
}
