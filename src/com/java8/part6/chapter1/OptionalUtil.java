package com.java8.part6.chapter1;

import java.util.Optional;

/**
 * @author Mr.zxb
 * @date 2019-04-08 10:37
 */
public class OptionalUtil {

    public static Optional<Integer> strintToInt(String s) {
        try {
            // 如果String能转换为对应的Integer，将其封装在Optional对象中返回
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            // 否则返回一个空的Optional对象
            return Optional.empty();
        }
    }
}
