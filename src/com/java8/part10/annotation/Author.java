package com.java8.part10.annotation;

import java.lang.annotation.Repeatable;

/**
 * 创建可重复注解
 * 1）将注解标记为@Repeatable
 * 2）提供一个注解的容器
 */
@Repeatable(Authors.class)
public @interface Author {

    String name();
}
