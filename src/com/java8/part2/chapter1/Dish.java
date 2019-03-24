package com.java8.part2.chapter1;

/**
 * @author Mr.zxb
 * @date 2019-03-08 20:45:47
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
