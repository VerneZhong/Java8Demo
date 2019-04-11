package com.java8.part7.completablefuture;

/**
 * 以枚举类型定义的折扣代码
 * @author Mr.zxb
 * @date 2019-04-11 11:25
 */
public class Discount {

    public enum Code {
        /**
         * 没折扣
         */
        NONE(0),
        /**
         * 银卡
         */
        SILVER(5),
        /**
         * 金卡
         */
        GOLD(10),
        /**
         * 铂金
         */
        PLATINUM(15),
        /**
         * 钻石
         */
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    /**
     * 将折扣代码应用用商品最初的原始价格
     * @param quote
     * @return
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getCode());
    }

    /**
     * 模拟Discount折扣服务响应延迟
     * @param price
     * @param code
     * @return
     */
    public static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }

}
