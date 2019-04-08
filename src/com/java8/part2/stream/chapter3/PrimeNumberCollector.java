package com.java8.part2.stream.chapter3;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 开发自己的收集器以获得更好的性能
 * 将前n个自然数按质数和非质数分区
 * @author Mr.zxb
 * @date 2019-04-01 14:32
 */
public class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**
     * 初始化累加器，返回HashMap
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(16) {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    /**
     *
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (booleanListMap, candidate) -> booleanListMap
                // 更加isPrime的结果，获取质数或非质数列表
                .get(isPrime(booleanListMap.get(true), candidate))
                // 将被检测数添加到相应的列表中
                .add(candidate);
    }

    /**
     * 合并两个结果
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map, map2) -> {
            map.get(true).addAll(map2.get(true));
            map.get(false).addAll(map2.get(false));
            return map;
        };
    }

    /**
     * 收集过程最后无需转换，因此用identity函数收尾
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 这个收集器是IDENTITY_FINISH，但既不是UNORDERED也不是CONCURRENT，因为质数是按顺序发现的
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     *
     * @param primes
     * @param candidate
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i = 0;
        for (A a : list) {
            if (!predicate.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static void main(String[] args) {

        Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, 20).boxed().collect(new PrimeNumberCollector());

        System.out.println(map);
    }
}
