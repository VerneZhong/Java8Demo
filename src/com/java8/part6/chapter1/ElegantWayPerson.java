package com.java8.part6.chapter1;

import java.util.Optional;

/**
 * 优雅的人
 * @author Mr.zxb
 * @date 2019-04-06 21:55:15
 */
public class ElegantWayPerson {

    private Optional<ElegantWayCar> car;

    public Optional<ElegantWayCar> getCar() {
        return car;
    }

    /**
     * 优雅的方式，代替if-else
     * @param optional
     * @return
     */
    public String getCarInsuranceName(Optional<ElegantWayPerson> optional) {
        return optional.flatMap(ElegantWayPerson::getCar)
                .flatMap(ElegantWayCar::getOptional)
                .map(Insurance::getName)
                .orElse("Unkown");
    }
}
