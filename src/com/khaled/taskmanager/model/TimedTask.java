package com.khaled.taskmanager.model;

public class TimedTask extends Task {

    private int durationMinutes; // مدة المهمة بالدقائق

    public TimedTask(int id, String title, String description, int durationMinutes) {
        super(id, title, description);
        this.durationMinutes = durationMinutes;
    }

    public TimedTask(int id, String title, String description, Deadline deadline, int durationMinutes) {
        super(id, title, description, deadline);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "TimedTask{" +
                "durationMinutes=" + durationMinutes +
                ", base=" + super.toString() +
                '}';
    }

}
