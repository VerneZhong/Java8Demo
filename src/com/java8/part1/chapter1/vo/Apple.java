package com.java8.part1.chapter1.vo;

/**
 * @author Mr.zxb
 * @date 2019-01-31 14:22
 */
public class Apple {

    private String color;
    private int weight;

    /**
     * 产地
     */
    private String country;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(String color, int weight, String country) {
        this.color = color;
        this.weight = weight;
        this.country = country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Apple=[color:" + color + ", weight:" + weight + "]";
    }
}
