package com.java8.part10.annotation;

import java.util.Arrays;

/**
 * 可重复注解，这种新的机制可以看成是语法糖
 * @author Mr.zxb
 * @date 2019-04-19 15:25
 */
@Author(name = "Java")
@Author(name = "C#")
@Author(name = "Scala")
public class Book {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        // 返回一个由重复注解Author组成的数组
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).forEach(a -> System.out.println(a.name()));
    }
}
