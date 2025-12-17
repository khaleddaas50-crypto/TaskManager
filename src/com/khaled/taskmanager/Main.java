package com.khaled.taskmanager;

import com.khaled.taskmanager.model.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("TaskManager started");

        Task task1 = new Task(1, "OOP Proje", "Task sinifi olustur");
        System.out.println(task1);

        task1.markCompleted();
        System.out.println("Tamamlandi: " + task1.isCompleted());
    }
}
