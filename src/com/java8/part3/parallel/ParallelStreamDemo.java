package com.java8.part3.parallel.chapter1;

import com.java8.part3.parallel.chapter2.ForkJoinSumCalculator;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流的使用
 * @author Mr.zxb
 * @date 2019-04-01 15:51
 */
public class ParallelStreamDemo {

    /**
     * 原始方式
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0L; i < n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 顺序流
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    /**
     * parallel是将顺序流转成并行流
     * sequential是将并行流转成顺序流
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long parallelSum2(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    public static void main(String[] args) {

        // 测量流性能
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreamDemo::sequentialSum, 1_000_000) + " msecs");
        // 原始方式，性能较好
        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreamDemo::iterativeSum, 1_000_000) + " msecs");
        // 涉及装箱影响性能
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreamDemo::parallelSum, 1_000_000) + " msecs");
        // 无装箱的性能损耗
        System.out.println("Parallel2 sum done in: " + measureSumPerf(ParallelStreamDemo::parallelSum2, 1_000_000) + " msecs");
        // 错误使用
        System.out.println("SideEffect sum done in: " + measureSumPerf(ParallelStreamDemo::sideEffectSum, 1_000_000) + " msecs");
        // fork/join框架的使用
        System.out.println("ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fast = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
//            System.out.println("sum = " + sum);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fast) {
                fast = duration;
            }
        }
        return fast;
    }

    /**
     * 正确使用并行流
     * @param n
     * @return
     */
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1L, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 错误的使用，严重
     */
    public static class Accumulator {

        public long total = 0;

        public void add(long value) {
            // 非原子的操作，并行会有竞争，导致数据错误
            total += value;
        }
    }
}

