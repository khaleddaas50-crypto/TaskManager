package com.khaled.taskmanager.model;
import com.khaled.taskmanager.model.Deadline;

public class Task {

    private int id;
    private String title;
    private String description;
    private boolean completed;
    private Deadline deadline;
    
    public Task(int id, String title, String description, Deadline deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.deadline = deadline;
    }

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }
    public boolean isCompleted() {
        return completed;
    }


    public void markCompleted() {
        this.completed = true;
    }
}
