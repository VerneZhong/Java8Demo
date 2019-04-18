package com.java8.part9.functional;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream的延迟计算
 * @author Mr.zxb
 * @date 2019-04-18 10:56
 */
public class StreamDelayCalc {

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * 这一方案有些笨拙，每次都需要遍历每个数字
     * @param n
     * @return
     */
    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(StreamDelayCalc::isPrime)
                .limit(n);
    }

    /**
     * 第一步：构造由数字组成的Stream
     * @return
     */
    static IntStream numbers(int i) {
        return IntStream.iterate(2, n -> n + 1).limit(i);
    }

    /**
     * 第二步：取得首元素
     * @param stream
     * @return
     */
    static int head(IntStream stream) {
        return stream.findFirst().getAsInt();
    }

    /**
     * 第三步：对尾部元素进行筛选
     * @param stream
     * @return
     */
    static IntStream tail(IntStream stream) {
        return stream.skip(1);
    }

    /**
     * 第四步：递归地创建由质数组成的Stream，此时运行会出现流关闭的异常，findFirst和skip将Stream切分成头尾两部分
     * @param stream
     * @return
     */
    static IntStream primes(IntStream stream) {
        int head = head(stream);
        return IntStream.concat(IntStream.of(head), primes(tail(stream).filter(n -> n % head != 0)));
    }

    @Test
    public void test() {
        primes(numbers(10));
    }
}
