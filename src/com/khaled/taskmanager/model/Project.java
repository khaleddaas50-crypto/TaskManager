package com.khaled.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int id;
    private String name;
    private List<Task> tasks;

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getIncompleteTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                result.add(task);
            }
        }
        return result;
    }
    public List<Task> getApproachingTasks() {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeadline() != null && task.getDeadline().isApproaching()) {
                result.add(task);
            }
        }

        return result;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskCount=" + (tasks != null ? tasks.size() : 0) +
                '}';
    }

    public boolean removeTaskById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }
}
