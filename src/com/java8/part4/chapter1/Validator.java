package com.java8.part4.chapter1;

/**
 * 策略模式应用用java8
 * @author Mr.zxb
 * @date 2019-04-03 16:13
 */
public class Validator {

    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s) {
        return validationStrategy.execute(s);
    }

    public static void main(String[] args) {

        // 用类的方式
        Validator validator = new Validator(new IsNumberic());
        boolean b1 = validator.validate("aaaa");

        Validator validator1 = new Validator(new IsAllLowerCase());
        boolean b2 = validator1.validate("bbbb");

        // 用lambda的方式
        Validator validator2 = new Validator(s -> s.matches("[a-z]+"));
        Validator validator3 = new Validator(s -> s.matches("\\d+"));

    }
}
