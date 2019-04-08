package com.java8.part7.future;

import java.util.concurrent.*;

/**
 * 在线商店
 * @author Mr.zxb
 * @date 2019-04-08 14:41
 */
public class Shop {

    /**
     * 随机数
     */
    private final ThreadLocalRandom random;

    /**
     * 执行异步任务线程池
     */
    private final ExecutorService executor;

    public Shop() {
        this.random = ThreadLocalRandom.current();
        this.executor = Executors.newCachedThreadPool();
    }

    /**
     * 获取价格--同步API
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 将同步方法-转换为异步方法
     * {@link CompletableFuture#complete(Object)}
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
//        // 创建CompletableFuture对象，它会包含计算的结果
//        CompletableFuture<Double> future = new CompletableFuture<>();
//
//        // 在另一个线程中以异步方式执行计算
//        // 需要长时间计算的任务结束并得出结果时，设置Future的返回值
//        executor.execute(() -> future.complete(calculatePrice(product)));
//
//        // 无需等待返回结果，直接返回future对象
//        return future;

        // 等价于下面

        // 使用CompletableFuture的工厂方法，使用默认ForkJoinPool线程池执行或是自定义线程池
        return CompletableFuture.supplyAsync(() -> calculatePrice(product), executor);
    }

    /**
     * 当异步方法出现异常，抛出内部异常
     * @param product
     * @return
     */
    public Future<Double> getPriceAsyncExceptionally(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();

        executor.execute(() -> {
            try {
                // 如果价格计算正常结束，完成Future操作并设置商品价格
                future.complete(calculatePrice(product));
            } catch (Exception e) {
                // 出现异常，就抛出导致失败的异常，完成这次Future操作
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    private double calculatePrice(String product) {
        // 耗时的查询数据库，获取价格
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟1秒的延迟
     */
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doSomeThingElse() {
        System.out.println("Handling other tasks...");
    }
}
