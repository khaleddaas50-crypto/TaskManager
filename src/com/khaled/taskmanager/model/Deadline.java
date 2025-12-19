package com.khaled.taskmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Deadline {

    private LocalDate date;

    public Deadline(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    // ✅ هاي الميثود الناقصة
    public boolean isApproaching() {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), date);
        return daysBetween >= 0 && daysBetween <= 3;
    }

    @Override
    public String toString() {
        return "Deadline{" +
                "date=" + date +
                '}';
    }
}
