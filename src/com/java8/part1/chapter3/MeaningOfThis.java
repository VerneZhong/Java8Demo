package com.java8.part1.chapter3;

/**
 * 匿名类谜题
 * @author Mr.zxb
 * @date 2019-03-01 11:17
 */
public class MeaningOfThis {

    public final int value = 4;

    public void doInt() {
        int value = 6;
        Runnable runnable = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                // 因为this标识的Runnable，并不是外部类
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();

        // 输出5
        meaningOfThis.doInt();
    }
}
