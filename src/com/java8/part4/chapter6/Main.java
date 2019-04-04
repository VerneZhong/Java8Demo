package com.java8.part4.chapter6;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:34
 */
public class Main {

    public static void main(String[] args) {

        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        // old
        System.out.println(15 == p2.getX());
        System.out.println(5 == p2.getY());

        // new  x > y
        int result = Point.compareByXAndThenY.compare(p1, p2);
        System.out.println(result);
    }
}
