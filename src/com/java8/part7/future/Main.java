package com.java8.part7.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Mr.zxb
 * @date 2019-04-08 15:04
 */
public class Main {

    public static void main(String[] args) {

        Shop shop = new Shop();

        long start = System.nanoTime();

        Future<Double> future = shop.getPriceAsync("my favorite product");

        long invocationTime = (System.nanoTime() - start) / 1_000_000;

        System.out.println("invocationTime = " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        shop.doSomeThingElse();

        // 在计算商品价格的同时
        try {
            // 从Future对象中读取价格，如果价格未知，会发生阻塞
            Double price = future.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("retrievalTime = " + retrievalTime + " msecs");
    }
}
