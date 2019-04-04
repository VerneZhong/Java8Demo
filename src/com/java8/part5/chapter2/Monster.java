package com.java8.part5.chapter2;

import com.java8.part5.chapter1.Resizable;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:10
 */
public class Monster implements Rotatable, Moveable, Resizable {

    private int x;
    private int y;

    @Override
    public int getWidth() {
        return this.x;
    }

    @Override
    public int getHeight() {
        return this.y;
    }

    @Override
    public void setWidth(int width) {
        this.x = width;
    }

    @Override
    public void setHeight(int height) {
        this.y = height;
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
        this.x += width;
        this.y += height;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setRotationAngle(int angleInDegress) {

    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    public static void main(String[] args) {

        Monster monster = new Monster();

        monster.rotateBy(180);

        monster.moveVertically(10);

        System.out.println(monster.getX());
        System.out.println(monster.getY());
    }
}
