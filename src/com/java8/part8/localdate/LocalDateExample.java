package com.java8.part8.localdate;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author Mr.zxb
 * @date 2019-04-11 15:57
 */
public class LocalDateExample {

    @Test
    public void test() {
        // 创建一个LocalDate对象并读取值
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfMonth();
        DayOfWeek dow = localDate.getDayOfWeek();
        // 一个月的长度
        int len = localDate.lengthOfMonth();
        // 是否闰年
        boolean leap = localDate.isLeapYear();
        System.out.printf("%s年%s月%s日%s周，一个月有%s天，是否闰年：%s", year, month.getValue(), day, dow.getValue(), len, leap);
    }

    @Test
    public void test2() {
        // 使用TemporalField读取LocalDate的值
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.printf("%s年%s月%s日", year, month, day);
    }

    @Test
    public void test3() {
        // 创建LocalTime并读取其值
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.printf("%s:%s:%s", hour, minute, second);

        // string转LocalDate、LocalTime
        LocalDate date = LocalDate.parse("2014-03-18");
        LocalTime localTime = LocalTime.parse("13:45:20");
    }

    @Test
    public void test4() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // 直接创建LocalDateTime对象，或者通过合并日期和时间的方式创建
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
    }

    @Test
    public void test5() {
        // 定义Duration
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now();

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();

        Instant i1 = Instant.now();
        Instant i2 = Instant.now();

        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(date1, date2);
        Duration d3 = Duration.between(i1, i2);

        // 创建Duration和Period对象
        // 创建3分钟的区间
        Duration threeMinutes = Duration.ofMinutes(3);
        // 等价于
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        // 10天
        Period tenDays = Period.ofDays(10);
        // 3周
        Period threeWeeks = Period.ofWeeks(3);
        // 2年6个月1天
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }


}
