package com.khaled.taskmanager;

import java.time.LocalDate;

import com.khaled.taskmanager.model.Deadline;
import com.khaled.taskmanager.model.Project;
import com.khaled.taskmanager.model.Task;
import com.khaled.taskmanager.model.TimedTask;

public class Main {

    public static void main(String[] args) {

        Project project = new Project(1, "OOP Project");

        Task task1 = new Task(1, "Task class", "Create Task class");
        Task task2 = new Task(2, "Project class", "Create Project class");

        project.addTask(task1);
        project.addTask(task2);

        Deadline deadline = new Deadline(LocalDate.now().plusDays(2));
        task2.setDeadline(deadline);

        System.out.println(task2.getDeadline());
        System.out.println("Yaklasan mi? " + task2.getDeadline().isApproaching());
        TimedTask timed = new TimedTask(3, "Timed task", "Inheritance test", 45);
        System.out.println(timed);
    }
}
