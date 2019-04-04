package com.java8.part6.chapter1;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:47
 */
public class Person {

    private Car car;

    public Car getCar() {
        return car;
    }

    public String getCarInsuranceName(Person person) {
        // 如何为缺失的值建模
//        return person.getCar().getInsurance().getName();

        // 采用防御式检查减少NPE
//        if (person != null) {
//            Car car = person.getCar();
//            if (car != null) {
//                Insurance insurance = car.getInsurance();
//                if (insurance != null) {
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unkown";

        // null-安全的第二种尝试：过多的退出语句
        if (person == null) {
            return "Unkown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unkown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unkown";
        }
        return insurance.getName();



    }
}
