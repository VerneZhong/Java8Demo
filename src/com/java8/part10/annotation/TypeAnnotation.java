package com.java8.part10.annotation;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型注解
 * @author Mr.zxb
 * @date 2019-04-19 15:56
 */
public class TypeAnnotation {

    public static void main(String[] args) {

        Book book = new Book();

        @NonNull String name = book.getName();

        System.out.println(name);

        List<Book> books = new ArrayList<>();

        books.add(null);

        System.out.println(books);
    }
}
