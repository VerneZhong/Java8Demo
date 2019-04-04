package com.java8.part4.chapter4;

/**
 * @author Mr.zxb
 * @date 2019-04-04 15:06
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
