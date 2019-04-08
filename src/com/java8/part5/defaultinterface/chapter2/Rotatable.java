package com.java8.part5.defaultinterface.chapter2;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:04
 */
public interface Rotatable {

    void setRotationAngle(int angleInDegress);

    int getRotationAngle();

    /**
     * 默认的旋转方法
     *
     * @param angleInDegress
     */
    default void rotateBy(int angleInDegress) {
        setRotationAngle((getRotationAngle() + angleInDegress) % 360);
    }
}
