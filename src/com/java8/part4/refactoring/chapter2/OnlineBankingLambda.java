package com.java8.part4.refactoring.chapter2;

import java.util.function.Consumer;

/**
 * @author Mr.zxb
 * @date 2019-04-03 16:27
 */
public class OnlineBankingLambda {

    /**
     * Java8方式
     * @param id
     * @param consumer
     */
    public void processCustomer(int id, Consumer<Customer> consumer) {
        Customer customer = Database.getCustomerWithId(id);
        consumer.accept(customer);
    }

    public static void main(String[] args) {

        new OnlineBankingLambda().processCustomer(1, c -> System.out.println("Hello " + c.getName()));
    }
}
