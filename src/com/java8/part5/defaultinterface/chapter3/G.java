package com.java8.part5.defaultinterface.chapter3;

/**
 * 冲突及如何显示的消除歧义
 * @author Mr.zxb
 * @date 2019-04-04 16:32
 */
public class G implements F, A {

    /**
     * 显式的调用接口中的方法
     */
    @Override
    public void hello() {
        // Java8引入的新语法： X.super.m(...)
        A.super.hello();
    }
}
