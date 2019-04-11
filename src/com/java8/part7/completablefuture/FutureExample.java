package com.java8.part7.completablefuture;

import java.util.concurrent.*;

/**
 * @author Mr.zxb
 * @date 2019-04-08 14:00
 */
public class FutureExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // 向线程池提交一个Callable对象
        Future<Double> future = executor.submit(FutureExample::doSomeLongComputation);

        // 继续执行其他任务
        doSomeThingElse();

        try {
            // 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒后就退出
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 计算抛出一个异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            // 在future对象完成之前超时异常
            e.printStackTrace();
        }
    }

    private static void doSomeThingElse() {
    }

    private static Double doSomeLongComputation() {
        return 0.1;
    }
}
