package com.java8.part3.parallel.chapter2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 继承RecursiveTask来创建可以用于分支/合并框架的任务
 * @author Mr.zxb
 * @date 2019-04-01 16:22
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /**
     * 不在将任务分解成子任务的数组大小
     */
    private static final long THRESHOLD = 10_000;

    /**
     * 要求和的数组
     */
    private final long[] numbers;

    /**
     * 子任务处理的数组起始位置
     */
    private final int start;

    /**
     * 子任务处理的数组终止位置
     */
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * 覆盖RecursiveTask方法
     * @return
     */
    @Override
    protected Long compute() {
        // 如果大小小于或等于阈值，顺序计算结果
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        // 创建一个子任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务，有可能允许进一步递归划分
        long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果尚未完成就等待
        long leftResult = leftTask.join();
        // 返回两个任务的结果
        return leftResult + rightResult;
    }

    /**
     * 在子任务不再可分时计算结果的简单算法
     * @return
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1L, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
