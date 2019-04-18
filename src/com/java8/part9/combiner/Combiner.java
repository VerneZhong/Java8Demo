package com.java8.part9.combiner;

import java.util.function.Function;

/**
 * 结合器是一种函数式的思想，它指的是将两个或多个函数或者数据结构进行合并
 * @author Mr.zxb
 * @date 2019-04-18 16:38
 */
public class Combiner<A, B, C> {

    /**
     * 函数组合
     * 接受函数f和g作为参数，并返回一个函数，实现的效果是先做f，接着做g
     * @param g
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    /**
     * 如果n的值为0，直接返回，否则执行函数f，重复执行n-1次，紧接着再执行一次
     * @param n
     * @param f
     * @param <A>
     * @return
     */
    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }

    public static void main(String[] args) {
        Integer in = repeat(3, (Integer x) -> 2 * x).apply(10);
        System.out.println(in);
    }
}
