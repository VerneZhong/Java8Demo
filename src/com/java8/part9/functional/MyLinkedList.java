package com.java8.part9.functional;

import java.util.function.Predicate;

/**
 * @author Mr.zxb
 * @date 2019-04-18 11:22
 */
public class MyLinkedList<T> implements MyList<T> {

    private final T head;
    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T getHead() {
        return head;
    }

    public MyList<T> getTail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public MyList<T> filter(Predicate<T> predicate) {
        return null;
    }

    static class Empty<T> implements MyList<T> {

        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> filter(Predicate<T> predicate) {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        MyList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    }

}
