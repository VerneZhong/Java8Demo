package com.java8.part9.patternmatching;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:55
 */
public class Main {

    public static void main(String[] args) {

        Expr expr = new BinOp("+", new Number(5), new Number(0));

        Expr match = Expr.simplify(expr);

        System.out.println(match);
    }
}
