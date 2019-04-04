package com.java8.part4.chapter4;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:07
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
