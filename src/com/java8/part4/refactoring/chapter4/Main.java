package com.java8.part4.refactoring.chapter4;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:08
 */
public class Main {

    public static void main(String[] args) {

        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        // 将两个对象链接起来
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println("result = " + result);

        // 使用lambda表达式
        UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println("result2 = " + result2);
    }
}
