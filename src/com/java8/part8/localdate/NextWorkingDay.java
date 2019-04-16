package com.java8.part8.localdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

/**
 * 实现一个定制的TemporalAdjuster
 * 能够计算明天的日期，同时过滤周六和周日这些节假日
 * @author Mr.zxb
 * @date 2019-04-16 14:50
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 读取当前的日期，周
        DayOfWeek week = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        // 如果当天是周五，增加3天
        if (week == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (week == DayOfWeek.SATURDAY) {
            // 如果是周六增加2天
            dayToAdd = 2;
        }
        // 增加恰当的天数后，返回修改的日期
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();

//        date = date.with(new NextWorkingDay());

        // 等价于
//        date = date.with(temporal -> {
//            // 读取当前的日期，周
//            DayOfWeek week = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//            int dayToAdd = 1;
//            // 如果当天是周五，增加3天
//            if (week == DayOfWeek.FRIDAY) {
//                dayToAdd = 3;
//            } else if (week == DayOfWeek.SATURDAY) {
//                // 如果是周六增加2天
//                dayToAdd = 2;
//            }
//            // 增加恰当的天数后，返回修改的日期
//            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//        });

        // 等价于
        TemporalAdjuster adjuster = TemporalAdjusters.ofDateAdjuster(temporal -> {
            // 读取当前的日期，周
            DayOfWeek week = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            // 如果当天是周五，增加3天
            if (week == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (week == DayOfWeek.SATURDAY) {
                // 如果是周六增加2天
                dayToAdd = 2;
            }
            // 增加恰当的天数后，返回修改的日期
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        date = date.with(adjuster);

        System.out.println("date = " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
