package com.khaled.taskmanager;

import java.time.LocalDate;

import com.khaled.taskmanager.model.Deadline;
import com.khaled.taskmanager.model.Notification;
import com.khaled.taskmanager.model.Priority;
import com.khaled.taskmanager.model.Project;
import com.khaled.taskmanager.model.Task;
import com.khaled.taskmanager.model.TimedTask;
import com.khaled.taskmanager.model.User;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== TaskManager Demo Basladi ===");

        // 1) Kullanici olustur
        User user = new User(1, "Khaled");

        // 2) Proje olustur ve kullaniciya ekle
        Project project = new Project(1, "OOP Proje");
        user.addProject(project);

        // 3) Task olustur (priority + deadline)
        Task task1 = new Task(1, "Task sinifi", "Task sinifini olustur", Priority.HIGH);
        task1.setDeadline(new Deadline(LocalDate.now().plusDays(2)));

        // 4) Task olustur (default priority) + deadline
        Task task2 = new Task(2, "Project sinifi", "Project sinifini olustur");
        task2.setPriority(Priority.MEDIUM);
        task2.setDeadline(new Deadline(LocalDate.now().plusDays(1)));
        System.out.println("Yaklaşan görevler:");
        for (Task t : project.getApproachingTasks()) {
            System.out.println(t);
        }


        // 5) TimedTask olustur (inheritance)
        TimedTask timedTask = new TimedTask(3, "TimedTask testi", "Inheritance testi", 45);
        timedTask.setPriority(Priority.LOW);

        // 6) Gorevleri projeye ekle
        project.addTask(task1);
        project.addTask(task2);
        project.addTask(timedTask);

        // 7) Bir gorevi tamamla
        task1.markCompleted();

        // 8) Yaklasan deadline icin bildirim (Notification) testi
        Notification n = task2.createDeadlineNotification();
        if (n != null) {
            n.send();
        } else {
            System.out.println("Bildirim yok (deadline yaklasmiyor veya deadline null).");
        }

        // 9) Ciktilar
        System.out.println("\n--- Kullanici ---");
        System.out.println(user);

        System.out.println("\n--- Proje ---");
        System.out.println("Proje adi: " + project.getName());
        System.out.println("Toplam gorev sayisi: " + project.getTasks().size());

        System.out.println("\n--- Tum Gorevler ---");
        for (Task t : project.getTasks()) {
            System.out.println(t);
        }

        System.out.println("\n--- Tamamlanmamis Gorevler ---");
        for (Task t : project.getIncompleteTasks()) {
            System.out.println(t);
        }

        System.out.println("\n=== Demo Bitti ===");
    }
}
