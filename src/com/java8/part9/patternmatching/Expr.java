package com.java8.part9.patternmatching;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Mr.zxb
 * @date 2019-04-18 15:26
 */
public class Expr {

    static <T> T patternMatchExpr(Expr expr, ThrFunction<String, Expr, Expr, T> binopcase,
                                  Function<Integer, T> numcase,
                                  Supplier<T> defaultcase) {
        return (expr instanceof BinOp) ?
                binopcase.apply(((BinOp) expr).getOpname(), ((BinOp) expr).getLeft(), ((BinOp) expr).getRight()) :
                (expr instanceof Number) ? numcase.apply(((Number) expr).getVal()) : defaultcase.get();
    }

    public static Expr simplify(Expr expr) {
        // 处理BinOp表达式
        ThrFunction<String, Expr, Expr, Expr> binopcase = (opname, left, right) -> {
            // 处理加法
            if ("+".equals(opname)) {
                if (left instanceof Number && ((Number) left).getVal() == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).getVal() == 0) {
                    return left;
                }
            }
            // 处理乘法
            if ("*".equals(opname)) {
                if (left instanceof Number && ((Number) left).getVal() == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).getVal() == 1) {
                    return left;
                }
            }
            return new BinOp(opname, left, right);
        };
        // 处理Number对象
        Function<Integer, Expr> numcase = val -> new Number(val);

        // 如果用户提供的Expr无法识别时进行的默认处理机制
        Supplier<Expr> defaultcase = () -> new Number(0);

        // 进行模式匹配
        return patternMatchExpr(expr, binopcase, numcase, defaultcase);
    }
}
