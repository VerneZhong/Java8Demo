package com.java8.part4.chapter2;

import java.util.function.Consumer;

/**
 * 模板方法应用
 * @author Mr.zxb
 * @date 2019-04-03 16:21
 */
public abstract class OnlineBanking {

    /**
     * 传统方式
     * @param id
     */
    public void processCustomer(int id) {
        Customer customer = Database.getCustomerWithId(id);
        makeCustomerHappy(customer);
    }


    abstract void makeCustomerHappy(Customer customer);

}
