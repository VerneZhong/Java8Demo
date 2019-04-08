package com.java8.part4.refactoring.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者的使用
 * @author Mr.zxb
 * @date 2019-04-03 16:41
 */
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }

    public static void main(String[] args) {

        Feed feed = new Feed();

        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());

        // lambda的使用
        feed.registerObserver(text -> System.out.println("Received the news " + text));

        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");

    }
}
