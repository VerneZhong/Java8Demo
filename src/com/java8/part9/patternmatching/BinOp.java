package com.java8.part9.patternmatching;

import java.util.function.Supplier;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:26
 */
public class BinOp extends Expr {

    private final String opname;
    private final Expr left, right;

    public BinOp(String opname, Expr left, Expr right) {
        this.opname = opname;
        this.left = left;
        this.right = right;
    }

    public String getOpname() {
        return opname;
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }

    public Expr accept(SimplifyExprVisitor visitor) {
        return visitor.visit(this);
    }

    static <T> T myIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

}
