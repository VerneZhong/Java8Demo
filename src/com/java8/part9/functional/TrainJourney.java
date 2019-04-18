package com.java8.part9.functional;

/**
 * 破坏式更新和函数式更新的比较
 * 模拟一个单向链接列表实现
 * @author Mr.zxb
 * @date 2019-04-18 10:08
 */
public class TrainJourney {

    private int price;
    private TrainJourney onward;

    public TrainJourney(int price, TrainJourney onward) {
        this.price = price;
        this.onward = onward;
    }

    /**
     * 不安全的链接方式
     * @param a
     * @param b
     * @return
     */
    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    /**
     * 用创建副本的方式替换前一种不安全方式，关于性能有争议
     * @param a
     * @param b
     * @return
     */
    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }


}
