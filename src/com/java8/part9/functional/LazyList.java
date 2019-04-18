package com.java8.part9.functional;

import org.junit.Test;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 一个基础的延时列表
 * @author Mr.zxb
 * @date 2019-04-18 11:24
 */
public class LazyList<T> implements MyList<T> {

    private final T head;
    private final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    /**
     * 注意，与前面的head不同，这里tail使用了一个Supplier方法提供了延迟性
     * 调用Supplier的get方法会触发延迟列表（lazyList）的节点创建，就像工厂会创建新的对象一样。
     * @return
     */
    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 传递一个Supplier作为LazyList的构造器的tail参数，创建由数字构成的无限延迟列表
     * @param n
     * @return
     */
    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    /**
     * 生成质数
     * 生成一个自定义的质数延迟列表
     * @param numbers
     * @return
     */
    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    /**
     * 实现一个延迟筛选器
     * @param predicate
     * @return
     */
    @Override
    public MyList<T> filter(Predicate<T> predicate) {
        return isEmpty() ?
                // 可以返回一个新的Empty<>()，不过这和返回一个空对象的效果是一样的
                new MyLinkedList.Empty<>() :
                predicate.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(predicate)) :
                        tail().filter(predicate);
    }

    /**
     * 打印输出所有的数字，该方法会递归地打印输出列表的头尾元素
     * @param list
     * @param <T>
     */
    public static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    /**
     * 递归的输出列表
     * @param list
     * @param <T>
     */
    public static <T> void recursivePrintAll(MyList<T> list) {
        if (list.isEmpty()) {
            return;
        }
        System.out.println(list.head());
        printAll(list.tail());
    }



    public static void main(String[] args) {
        LazyList<Integer> list = from(2);
        // 无限延迟数字链表
//        int two = list.head;
//        int three = list.tail().head();
//        int four = list.tail().tail().head();
//        int five = list.tail().tail().tail().head();
//        System.out.println(two +" " + three + " " + four + " " + five);

        // 无限延迟质数列表
//        int two = primes(list).head();
//        int three = primes(list).tail().head();
//        int five = primes(list).tail().tail().head();
//        System.out.println(two +" " + three + " " + five);

        // 打印质数
//        printAll(primes(list));
        recursivePrintAll(primes(list));
    }

}
