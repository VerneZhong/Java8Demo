package com.java8.part2.stream.chapter1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 领域：交易员和交易
 * @author Mr.zxb
 * @date 2019-03-10 21:12:49
 */
public class FieldExample {

    public static void main(String[] args) {

        // 剑桥 - raoul
        Trader raoul = new Trader("Raoul", "Cambridge");
        // 米兰 - mario
        Trader mario = new Trader("Mario", "Milan");
        //
        Trader alan = new Trader("Alan", "Cambridge");
        //
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1、找出2011发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> q1 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(t -> t.getValue())).collect(toList());
        print(q1);

        // 2、交易员都在哪些不同的城市工作过
        List<String> q2 = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        print(q2);

        // 3、查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> q3 = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Cambridge")).distinct().sorted(comparing(t -> t.getName())).collect(toList());
        print(q3);

        // 4、返回所有交易员的姓名字符串，按字母顺序排序
        List<String> q4 = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted(String::compareTo).collect(toList());
        String q4Str = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted(String::compareTo).collect(Collectors.joining());
        print(q4);
        System.out.println(q4Str);

        // 5、有没有交易员是在米兰工作的？
        boolean q5 = transactions.stream().anyMatch(t -> Objects.equals(t.getTrader().getCity(), "Milan"));
        System.out.println(q5);

        // 6、打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);

        // 7、所有交易中，最高的交易额是多少？
        Optional<Integer> q7 = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(q7.get());

        // 8、找到交易额最小的交易。
        Optional<Transaction> q8 = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        // 等价于
        Optional<Transaction> q8_1 = transactions.stream().min(comparing(Transaction::getValue));

        System.out.println(q8.get());
        System.out.println(q8_1.get());

    }

    public static <T> void print(List<T> list) {
        System.out.println(list);
    }
}
