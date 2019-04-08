package com.java8.part6.chapter1;

/**
 * 保险
 * @author Mr.zxb
 * @date 2019-04-04 16:47
 */
public class Insurance {

    private String name;

    public String getName() {
        return name;
    }

    public Insurance setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
