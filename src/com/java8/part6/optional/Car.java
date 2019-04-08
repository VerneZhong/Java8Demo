package com.java8.part6.optional;

import java.util.Optional;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:47
 */
public class Car {

    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }

    public Optional<Insurance> getInsuranceAsOptional() {
        return Optional.ofNullable(insurance);
    }
}
