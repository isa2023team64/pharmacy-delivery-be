package com.isa2023team64.pharmacydeliverybe.util;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSpan {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime; // minutes

    private TimeSpan(LocalDateTime startTime, Integer duration) {
        this.startTime = startTime;
        this.endTime = this.startTime.plusMinutes(duration);
    }

    public static TimeSpan of(LocalDateTime startTime, Integer duration) {
        return new TimeSpan(startTime, duration);
    }

    public Integer duration() {
        return (int)Duration.between(startTime, endTime).toMinutes();
    }

    public boolean overlaps(LocalDateTime startTime, Integer duration) {
        var timeSpan = TimeSpan.of(startTime, duration);
        return overlaps(timeSpan);
    }

    public boolean overlaps(TimeSpan timeSpan) {
        return (timeSpan.getStartTime().isAfter(startTime) && timeSpan.getStartTime().isBefore(getEndTime())) ||
               (timeSpan.getEndTime().isAfter(startTime) && timeSpan.getEndTime().isBefore(getEndTime())) ||
               (timeSpan.getStartTime().isEqual(startTime) && timeSpan.getEndTime().isAfter(startTime)) ||
               (timeSpan.getEndTime().isEqual(getEndTime()) && timeSpan.getStartTime().isBefore(startTime));
    }

    public boolean isInFuture() {
        return this.startTime.isAfter(LocalDateTime.now());
    }

}
