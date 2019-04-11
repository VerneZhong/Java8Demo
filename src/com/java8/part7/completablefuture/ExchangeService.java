package com.java8.part7.completablefuture;

/**
 * 模拟远程汇率服务
 * @author Mr.zxb
 * @date 2019-04-11 14:39
 */
public class ExchangeService {

    public enum Money {
        /**
         * 欧元
         */
        EUR,
        /**
         * 美元
         */
        USD;
    }

    public static double getRate(Money money, Money money2) {
        Shop.delay();
        return 1.13;
    }

}
