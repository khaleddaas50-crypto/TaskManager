package com.khaled.taskmanager.model;
import com.khaled.taskmanager.model.Deadline;
import com.khaled.taskmanager.model.Notification;
import com.khaled.taskmanager.model.Priority;

public class Task {

    private int id;
    private String title;
    private String description;
    private boolean completed;
    private Deadline deadline;
    private Priority priority;

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
    
    public Notification createDeadlineNotification() {
        if (deadline != null && deadline.isApproaching()) {
            return new Notification("Task '" + title + "' deadline is approaching");
        }
        return null;
    }
    public Task(int id, String title, String description, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.priority = priority;        
    }
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


}
