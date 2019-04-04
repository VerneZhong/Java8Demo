package com.java8.part5.chapter3;

/**
 *  类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 *  如果无法根据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口
 *  如果还无法判断，继承了多个接口的类必须通过显示覆盖和调用期望的方法。
 * @author Mr.zxb
 * @date 2019-04-04 16:14
 */
public class C implements A, B {

    public static void main(String[] args) {

        new C().hello();

    }
}
