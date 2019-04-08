package com.java8.part4.refactoring.chapter3;

/**
 * @author Mr.zxb
 * @date 2019-04-03 16:37
 */
public class NYTimes implements Observer {
    @Override
    public void notify(String text) {
        if (text != null && text.contains("money")) {
            System.out.println("Breaking news in NY!  " + text);
        }
    }
}

class Guardian implements Observer {

    @Override
    public void notify(String text) {
        if (text != null && text.contains("queen")) {
            System.out.println("Yet another news in London... " + text);
        }
    }
}

class LeMonde implements Observer {

    @Override
    public void notify(String text) {
        if (text != null && text.contains("wine")) {
            System.out.println("Today cheese, wine and news!  " + text);
        }
    }
}
