package com.java8.part6.optional;

import java.util.Optional;

/**
 * @author Mr.zxb
 * @date 2019-04-08 10:25
 */
public class Main {

    public static void main(String[] args) {

        Optional<Person> person = Optional.of(new Person());

//        new Car();
        Optional<Car> car = Optional.ofNullable(null);

        Person.recommendNullSafeFindCheapestInsurance(person, car).ifPresent(System.out::println);
    }
}
