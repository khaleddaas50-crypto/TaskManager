package com.khaled.taskmanager.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Deadline sinifi bir gorevin son teslim tarihini temsil eder.
 */

public class Deadline {

    private LocalDate date;

    public Deadline(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Deadline tarihinin yaklasip yaklasmadigini kontrol eder.
     *
     * @return true eger deadline yakinsa
     */
    public boolean isApproaching() {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), date);
        return daysBetween >= 0 && daysBetween <= 3;
    }

    @Override
    public String toString() {
        return "Deadline{date=" + date + "}";
    }
}
