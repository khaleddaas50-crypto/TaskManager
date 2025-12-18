package com.khaled.taskmanager;

import com.khaled.taskmanager.model.Project;
import com.khaled.taskmanager.model.Task;

public class Main {

    public static void main(String[] args) {
    	Project project = new Project(1, "OOP Project");
    	Task task1 = new Task(1, "Task class", "Create Task class");
    	Task task2 = new Task(2, "Project class", "Create Project class");

    	project.addTask(task1);
    	project.addTask(task2);

    	task1.markCompleted();

    	System.out.println(project.getIncompleteTasks());

    }
}
