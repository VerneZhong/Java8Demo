package com.java8.part5.defaultinterface.chapter1;

/**
 * 默认方法的使用
 * @author Mr.zxb
 * @date 2019-04-04 15:54
 */
public interface Resizable {
    // 初始API

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    // 第二版API，新增接口

    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }

}
