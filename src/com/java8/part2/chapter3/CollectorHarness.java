package com.java8.part2.chapter3;

import com.java8.part2.chapter2.PartitioningByDemo;

/**
 * @author Mr.zxb
 * @date 2019-04-01 15:32
 */
public class CollectorHarness {

    public static void main(String[] args) {

        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {

            long start = System.nanoTime();

            // jdk类库
//            PartitioningByDemo.partitionPrimes(1_000_000);

            // 自定义
            PartitioningByDemo.primeNumberCollector(1_000_000);

            long duration = (System.nanoTime() - start) / 1_000_000;

            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println(fastest);
    }
}
