package com.babor;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MasterDateTime {
    public static void test() {
        Date date = new Date();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = Instant.now();
        Instant sixHoursBackInstant = instant.minus(7, ChronoUnit.HOURS);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        Instant instant1 = localDateTime.atZone(ZoneId.of("UTC")).toInstant();
        LocalDateTime localDateTime1 = localDate.atStartOfDay(ZoneId.of("UTC")).toLocalDateTime();

        System.out.println(instant1);
        System.out.println(localDateTime1);
        System.out.println();

        System.out.println("Date:: " + date);
        System.out.println("LocalDate:: " + localDate);
        System.out.println("LocalDateTime:: " + localDateTime);
        System.out.println("Instant:: " + instant);
        System.out.println("6 Hours back Instant:: " + sixHoursBackInstant);
        System.out.println("ZonedDateTime:: " + zonedDateTime);
    }
}
