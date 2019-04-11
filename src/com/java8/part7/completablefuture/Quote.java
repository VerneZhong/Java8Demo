package com.java8.part7.completablefuture;

/**
 * 折扣服务
 * @author Mr.zxb
 * @date 2019-04-11 13:45
 */
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s) {
        String[] strings = s.split(":");
        String shotName = strings[0];
        double price = Double.parseDouble(strings[1]);
        Discount.Code code = Discount.Code.valueOf(strings[2]);
        return new Quote(shotName, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
