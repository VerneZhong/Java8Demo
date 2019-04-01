package com.java8.part2.chapter3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义收集器
 * @author Mr.zxb
 * @date 2019-04-01 14:08
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 建立新的结果容器
     * 创建集合操作的起始点
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     * 累积遍历过的项目，原位修改累加器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 合并两个结果容器
     * 将累加器的内容合并
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list, list2) -> {
            list.addAll(list2);
            return list;
        };
    }

    /**
     * 对结果容器应用最终转换
     * 恒等函数
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器的行为，尤其是关于流是否可以并行归约
     * CONCURRENT：归约结果不受流中项目的遍历和累积顺序的影响
     * UNORDERED ：accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。
     * IDENTITY_FINISH：这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> evens = numbers.stream().filter(i -> i % 2 == 0).collect(new ToListCollector<>());

        // Stream重载的collect方法可以接收另外三个函数--supplier、accumulate和combiner
        List<Integer> odds = numbers.stream().filter(i -> i % 2 != 0).collect(
                // 供应源
                ArrayList::new,
                // 累加器
                List::add,
                // 组合器
                List::addAll);

        System.out.println(evens);
        System.out.println(odds);
    }
}
