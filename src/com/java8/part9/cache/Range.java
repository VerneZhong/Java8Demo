package com.java8.part9.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 缓存或记忆表
 * 解决那种昂贵的访问开销
 *
 * @author Mr.zxb
 * @date 2019-04-18 16:13
 */
public class Range {

    private final int key;

    public Range(int key) {
        this.key = key;
    }

    final Map<Range, Integer> numberOrNodes = new HashMap<>();

    /**
     * 传统方式
     *
     * @param range
     * @return
     */
    Integer computeNumberOrNodesUsingCache(Range range) {
        Integer result = numberOrNodes.get(range);
        if (result != null) {
            return result;
        }
        result = computeNumberOrNodes(range);
        numberOrNodes.put(range, result);
        return result;
    }

    Integer computeNumberOrNodes(Range range) {
        return ThreadLocalRandom.current().nextInt(10);
    }

    /**
     * Java8改进了Map接口，实现更简洁
     *
     * @param range
     * @return
     */
    Integer computeNumberOrNodesUsingCacheAsJava8(Range range) {
        return numberOrNodes.computeIfAbsent(range, this::computeNumberOrNodes);
    }

    public static void main(String[] args) {

        Range range = new Range(2);

        System.out.println(range.computeNumberOrNodesUsingCacheAsJava8(range));
        System.out.println(range.computeNumberOrNodesUsingCacheAsJava8(range));

    }
}
