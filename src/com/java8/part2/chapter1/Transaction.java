package com.java8.part2.chapter1;

/**
 * 交易
 * @author Mr.zxb
 * @date 2019-03-10 21:09:56
 */
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " + "year: " + this.year + ", " + "value: " + this.value + "}";
    }
}
