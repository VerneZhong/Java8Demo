package com.java8.part5.defaultinterface.chapter2;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:07
 */
public interface Moveable {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    /**
     * 水平移动
     * @param distance
     */
    default void moveHorizontally(int distance) {
        setX(getX() + distance);
    }

    /**
     * 垂直移动
     * @param distance
     */
    default void moveVertically(int distance) {
        setY(getY() + distance);
    }

}
