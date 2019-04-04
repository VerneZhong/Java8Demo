package com.java8.part4.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 工厂模式
 *
 * @author Mr.zxb
 * @date 2019-04-04 15:16
 */
public class ProductFactory {

    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }

    private static final Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct2(String name) {
        Supplier<Product> supplier = map.get(name);
        if (supplier != null) {
            return supplier.get();
        }
        throw new RuntimeException("No such product " + name);
    }
}
