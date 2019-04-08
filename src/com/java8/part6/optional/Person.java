package com.java8.part6.optional;

import java.util.Optional;

/**
 * @author Mr.zxb
 * @date 2019-04-04 16:47
 */
public class Person {

    private Car car;

    private int age;

    public Car getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    /**
     * Optional不支持序列化，不要作为字段使用
     * @return
     */
    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(this.car);
    }

    /**
     * 如何解决null的使用，以及引发的npe问题
     * @param person
     * @return
     */
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
//        if (person == null) {
//            return "Unkown";
//        }
//        Car car = person.getCar();
//        if (car == null) {
//            return "Unkown";
//        }
//        Insurance insurance = car.getInsurance();
//        if (insurance == null) {
//            return "Unkown";
//        }
//        return insurance.getName();

        // Java8的优雅方式
        return Optional.ofNullable(person).flatMap(Person::getCarAsOptional)
                .flatMap(Car::getInsuranceAsOptional)
                .map(Insurance::getName)
                .orElse("Unkown");
    }

    /**
     * 使用filter剔除特定的值
     * @param person
     * @param minAge
     * @return
     */
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCarAsOptional)
                .flatMap(Car::getInsuranceAsOptional)
                .map(Insurance::getName)
                .orElse("Unkown");
    }

    public static Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        return cheapestCompany();
    }

    private static Insurance cheapestCompany() {
        return new Insurance().setName("全能车险");
    }

    /**
     * 不推荐的方式，和原始方式if-else并无变化
     * @param person
     * @param car
     * @return
     */
    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * 推荐的优雅方式，两个Optional相结合
     * @param person
     * @param car
     * @return
     */
    public static Optional<Insurance> recommendNullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }


}
