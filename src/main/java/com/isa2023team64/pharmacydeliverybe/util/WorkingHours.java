package com.isa2023team64.pharmacydeliverybe.util;

import java.time.LocalTime;

public class WorkingHours {
    public LocalTime openingTime;
    public LocalTime closingTime;

    private WorkingHours(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    private WorkingHours(Integer openingHour, Integer openingMinute, Integer openingSecond, Integer closingHour, Integer closingMinute, Integer closingSecond) {
        this(LocalTime.of(openingHour, openingMinute, openingSecond), LocalTime.of(closingHour, closingMinute, closingSecond));
    }

    public static WorkingHours of(LocalTime openingTime, LocalTime closingTime) {
        return new WorkingHours(openingTime, closingTime);
    }

    public static WorkingHours of(Integer openingHour, Integer openingMinute, Integer openingSecond, Integer closingHour, Integer closingMinute, Integer closingSecond) {
        return new WorkingHours(openingHour, openingMinute, openingSecond, closingHour, closingMinute, closingSecond);
    }

    public static WorkingHours of(String openingTimeFormatted, String closingTimeFormatted) {
        return of(parseTime(openingTimeFormatted), parseTime(closingTimeFormatted));
    }

    public static String formatTime(LocalTime time) {
        return time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }

    public String getOpeningTimeFormatted() {
        return formatTime(openingTime);
    }

    public String getClosingTimeFormatted() {
        return formatTime(openingTime);
    }

    public static LocalTime parseTime(String timeFormatted) {
        String[] timeComponents = timeFormatted.split(":");

        int hours = Integer.parseInt(timeComponents[0]);
        int minutes = Integer.parseInt(timeComponents[1]);
        int seconds = Integer.parseInt(timeComponents[2]);

        return LocalTime.of(hours, minutes, seconds);
    }
}
