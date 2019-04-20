package com.java8.part2.stream.chapter1;

import lombok.Data;
import lombok.ToString;

/**
 * @author Mr.zxb
 * @date 2019-03-08 20:45:47
 */
@ToString
@Data
public class Dish {

    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    /**
     * 分类
     */
    public enum Type {
        // 肉食
        MEAT,
        // 鱼类
        FISH,
        // 其他
        OTHER;
    }

    /**
     * 热量等级
     */
    public enum CaloricLevel {
        // 低热量
        DIET,
        // 普通
        NORMAL,
        // 高热量
        FAT;
    }

}
