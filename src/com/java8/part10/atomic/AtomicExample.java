package com.java8.part10.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 多线程环境中，如果多个线程需要频繁地进行更新操作，且很少有读取的动作，Java API文档推荐使用类LongAdder、LongAccumulator、
 * DoubleAdder以及DoubleAccumulator，尽量避免使用它们对应的原子类型。这些类在设计之初就考虑了动态增长的需求，
 * 可以有效地减少线程间的竞争
 * @author Mr.zxb
 * @date 2019-04-19 16:40
 */
public class AtomicExample {

    public static void main(String[] args) throws InterruptedException {

        // 使用LongAdder计算多个值之和

        // 使用默认构造器，初始的sum值被置为0
        LongAdder adder = new LongAdder();

        // 在多个不同的线程中执行加法运算
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> adder.add(10));
            t.start();
            t.join();
        }
        // 取出某个时刻得出的sum的值
        long sum = adder.sum();
        System.out.println(sum);


        // 使用LongAccumulator计算多个值之和
        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
        // 在多个不同的线程中执行加法运算
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> accumulator.accumulate(10));
            t.start();
            t.join();
        }
        // 取出某个时刻得出的sum的值
        long result = accumulator.get();
        System.out.println(result);
    }
}
