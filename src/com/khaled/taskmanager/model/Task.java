package com.khaled.taskmanager.model;
/**
 * Task sinifi bir gorevi temsil eder.
 * Her task bir baslik, aciklama, oncelik ve deadline bilgisine sahiptir.
 * Completable arayuzunu uygular.
 */

public class Task {

    private int id;
    private String title;
    private String description;
    private boolean completed;
    private Deadline deadline;
    private Priority priority;
    private String assignedUserName;

    public Task(int id, String title, String description, Deadline deadline) {
    	if (id <= 0) {
    	    throw new IllegalArgumentException("Task id must be positive");
    	}
    	if (title == null || title.trim().isEmpty()) {
    	    throw new IllegalArgumentException("Task title cannot be empty");
    	}

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
    /**
     * Gorevin tamamlanip tamamlanmadigini kontrol eder.
     *
     * @return true eger gorev tamamlandiysa
     */

    public boolean isCompleted() {
        return completed;
    }

    /**
     * Gorevi tamamlanmis olarak isaretler.
     */

    public void markCompleted() {
        this.completed = true;
    }
    /**
     * Deadline yaklasiyorsa bildirim olusturur.
     *
     * @return Notification nesnesi veya null
     */

    public Notification createDeadlineNotification() {
        if (deadline != null && deadline.isApproaching()) {
            return new Notification("Task '" + title + "' deadline is approaching");
        }
        return null;
    }
    public Task(int id, String title, String description, Priority priority) {
    	if (id <= 0) {
    	    throw new IllegalArgumentException("Task id must be positive");
    	}
    	if (title == null || title.trim().isEmpty()) {
    	    throw new IllegalArgumentException("Task title cannot be empty");
    	}

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

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", assignedUserName=" + (assignedUserName != null ? assignedUserName : "NONE") +
                ", priority=" + (priority != null ? priority : "NONE") +
                ", deadline=" + (deadline != null ? deadline.getDate() : "NONE") +
                '}';
    }

	public int getId() {
	    return id;
	}
	public String getTitle() {
	    return title;
	}


}
