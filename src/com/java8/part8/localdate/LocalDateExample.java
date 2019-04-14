package com.java8.part8.localdate;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

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

    @Test
    public void test6() {
        // 以比较直观的方式操作LocalDate属性
        LocalDate date1 = LocalDate.of(2014, 3, 18);

        // 修改年份， 2011-03-18
        LocalDate date2 = date1.withYear(2011);

        // 修改为25号，2011-03-25
        LocalDate date3 = date2.withDayOfMonth(25);

        // 修改为9月，2011-09-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
    }

    @Test
    public void test7() {
        // 以相对方式修改LocalDate对象属性
        LocalDate date = LocalDate.of(2014, 3, 18);
        // 增加一周， 2014-03-25
        LocalDate date2 = date.plusWeeks(1);
        // 减少3年，2011-03-25
        LocalDate date3 = date2.minusYears(3);
        // 增加6个月，2011-09-25
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
    }

    @Test
    public void test8() {
        // 初始化，2014-03-18
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        // 2014-09-18
        date1 = date1.with(ChronoField.MONTH_OF_YEAR, 9);
        // 2016-09-08
        date1 = date1.plusYears(2).minusDays(10);
        //
        date1.withYear(2011);

        System.out.println(date1.format(DateTimeFormatter.BASIC_ISO_DATE));
    }

    @Test
    public void test9() {
        // 使用预定义的TemporalAdjuster
        LocalDate date = LocalDate.of(2014, 3, 18);
        // 2014-03-23
        LocalDate date2 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        // 2014-03-31
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("date3 = " + date3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
