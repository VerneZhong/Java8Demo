package com.java8.part4.refactoring.chapter3;

/**
 * @author Mr.zxb
 * @date 2019-04-03 16:40
 */
public interface Subject {

    void registerObserver(Observer observer);

    void notifyObservers(String tweet);
}
