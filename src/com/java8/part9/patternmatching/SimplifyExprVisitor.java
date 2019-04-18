package com.java8.part9.patternmatching;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:28
 */
public class SimplifyExprVisitor {

    public Expr visit(BinOp e) {
        if ("+".equals(e.getOpname()) && e.getRight() instanceof Number) {
            return e.getLeft();
        }
        return e;
    }
}
