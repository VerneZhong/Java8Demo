package com.java8.part9.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.LongStream;

/**
 * 函数式编程实战
 * 给定一个列表List<value> 构造一个List<List<Integer>>
 * @author Mr.zxb
 * @date 2019-04-16 16:12
 */
public class FunctionalProgramming {

    public static List<List<Integer>> subsets(List<Integer> list) {
        // 如果输入为空，它就只包含一个子集，既空列表自身
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        // 否则就取出一个元素first，找出剩余部分的所有子集，并将其赋予subans. subans构成了结果的另外一半
        List<List<Integer>> subans = subsets(rest);
        // 答案的另一半是subans2,它包含了subans中的所有列表，但是经过调整，在每个列表的第一个元素之前添加了first
        List<List<Integer>> subans2 = insertAll(first, subans);
        // 将两个子答案整合在一起就完成了任务，简单吗?
        return concat(subans, subans2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans, List<List<Integer>> subans2) {
        // 创建一个List对象副本
        List<List<Integer>> result = new ArrayList<>(subans);
        result.addAll(subans2);
        return result;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : subans) {
            // 复制列表，从而使你有机会对其进行添加操作。即使底层是可变的，你也应该复制底层的结构
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }

    /**
     * 迭代式的阶乘计算
     * @param n
     * @return
     */
    static int factorialIterative(int n) {
        int r = 1;
        for (int i = 0; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * 递归式的阶乘计算
     * @param n
     * @return
     */
    static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    /**
     * 基于Stream的阶乘
     * @param n
     * @return
     */
    static long factorialStream(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    /**
     * 基于“尾-递”的阶乘，解决大型递归Stack Overflow Error
     * @param n
     * @return
     */
    static long factorialTailRecursive(int n) {
        return factorialHelper(1, n);
    }

    /**
     * 尾递类型的函数，原因是递归调用发生在方法的最后
     * @param acc
     * @param n
     * @return
     */
    static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

    /**
     * 科里化是一种将具备2个参数（比如，x和y）的函数f转化为使用一个参数的函数g，并且这个函数的返回值也是一个函数，
     * 它作为新函数的一个参数。
     * 单位转换通常都会涉及转换因子以及基线调整因子的问题
     * 将摄氏度转换到华氏度的公式是CtoF(X) = X * 9 / 5 + 32.
     * @param x
     * @param f 转换因子
     * @param b 基线值
     * @return
     */
    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    /**
     * 工厂方法，
     * @param f
     * @param b
     * @return
     */
    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return x -> x * f + b;
    }

    @Test
    public void test() {
        // 自由组合
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        System.out.println(gbp);
    }
}
