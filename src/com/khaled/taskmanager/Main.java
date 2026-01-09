package com.khaled.taskmanager;

import com.khaled.taskmanager.model.FileExporter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

        Scanner sc = new Scanner(System.in);

        int nextUserId = 1;
        int nextProjectId = 1;
        int nextTaskId = 1;

        List<User> users = new ArrayList<>();
        List<Project> projects = new ArrayList<>();

        while (true) {

            System.out.println("\n1) Kullanici olustur");
            System.out.println("2) Proje olustur");
            System.out.println("3) Task olustur (priority + deadline)");
            System.out.println("4) TimedTask olustur (inheritance)");
            System.out.println("5) Yaklasan gorevleri listele");
            System.out.println("6) Bir gorevi tamamla");
            System.out.println("7) Yaklasan deadline icin bildirim (Notification) testi");
            System.out.println("8) Ciktilar");
            System.out.println("9) Export");
            System.out.println("10) Gorev sil (Task sil)");
            System.out.println("11) Proje sil");
            System.out.println("0) Cikis");

            int choice = readNumberOnly(sc, "Secim: ");

            if (choice == 0) break;

            if (choice == 1) {
                // 1) Kullanici olustur
                System.out.print("Kullanici adi girin: ");
                String userName = sc.nextLine();

                User newUser = new User(nextUserId++, userName);
                users.add(newUser);

                System.out.println("User olusturuldu: " + newUser.getId() + " - " + newUser.getName());
            }

            else if (choice == 2) {
                // 2) Proje olustur
                System.out.print("Proje adi girin: ");
                String projectName = sc.nextLine();

                Project newProject = new Project(nextProjectId++, projectName);
                projects.add(newProject);

                System.out.println("Proje olusturuldu: " + newProject.getId() + " - " + newProject.getName());
            }

            else if (choice == 3) {
                // 3) Task olustur (priority + deadline)
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }
                if (users.isEmpty()) {
                    System.out.println("Once kullanici olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);
                User assignedUser = selectUserById(sc, users);

                System.out.print("Task baslik girin: ");
                String title = sc.nextLine();
                System.out.print("Task aciklama girin: ");
                String desc = sc.nextLine();

                Priority pr = readPriority(sc);
                int days = readNumberOnly(sc, "Deadline kac gun sonra? ");

                Task task = new Task(nextTaskId++, title, desc, pr);
                task.setDeadline(new Deadline(LocalDate.now().plusDays(days)));
                task.setAssignedUserName(assignedUser.getName());

                selectedProject.addTask(task);
                System.out.println("Task eklendi: " + task.getId() + " (Proje: " + selectedProject.getName() + ")");
            }

            else if (choice == 4) {
                // 4) TimedTask olustur (inheritance)
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }
                if (users.isEmpty()) {
                    System.out.println("Once kullanici olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);
                User assignedUser = selectUserById(sc, users);

                System.out.print("TimedTask baslik girin: ");
                String title = sc.nextLine();
                System.out.print("TimedTask aciklama girin: ");
                String desc = sc.nextLine();

                int duration = readNumberOnly(sc, "Sure (dakika): ");
                Priority pr = readPriority(sc);
                int days = readNumberOnly(sc, "Deadline kac gun sonra? ");

                TimedTask timedTask = new TimedTask(nextTaskId++, title, desc, duration);
                timedTask.setPriority(pr);
                timedTask.setDeadline(new Deadline(LocalDate.now().plusDays(days)));
                timedTask.setAssignedUserName(assignedUser.getName());

                selectedProject.addTask(timedTask);
                System.out.println("TimedTask eklendi: " + timedTask.getId() + " (Proje: " + selectedProject.getName() + ")");
            }

            else if (choice == 5) {
                // 5) Yaklasan gorevleri listele
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                System.out.println("\n--- Yaklasan Gorevler (Proje: " + selectedProject.getName() + ") ---");
                for (Task t : selectedProject.getApproachingTasks()) {
                    System.out.println(t);
                }
            }

            else if (choice == 6) {
                // 6) Bir gorevi tamamla
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                if (selectedProject.getTasks().isEmpty()) {
                    System.out.println("Bu projede task yok.");
                    continue;
                }

                int taskId = readNumberOnly(sc, "Tamamlanacak task ID: ");
                boolean found = false;

                for (Task t : selectedProject.getTasks()) {
                    if (t.getId() == taskId) {
                        t.markCompleted();
                        found = true;
                        System.out.println("Task tamamlandi: " + taskId + " (Proje: " + selectedProject.getName() + ")");
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Task bulunamadi.");
                }
            }

            else if (choice == 7) {
                // 7) Yaklasan deadline icin bildirim (Notification) testi
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                if (selectedProject.getTasks().isEmpty()) {
                    System.out.println("Bu projede task yok.");
                    continue;
                }

                int taskId = readNumberOnly(sc, "Bildirim icin task ID: ");
                Task selected = null;

                for (Task t : selectedProject.getTasks()) {
                    if (t.getId() == taskId) {
                        selected = t;
                        break;
                    }
                }

                if (selected == null) {
                    System.out.println("Task bulunamadi.");
                    continue;
                }

                Notification n = selected.createDeadlineNotification();
                if (n != null) {
                    n.send();
                } else {
                    System.out.println("Bildirim yok (deadline yaklasmiyor veya deadline null).");
                }
            }

            else if (choice == 8) {
                // 8) Ciktilar
                if (!users.isEmpty()) {
                    System.out.println("\n--- Kullanicilar ---");
                    for (User u : users) {
                        System.out.println(u.getId() + " - " + u.getName());
                    }
                } else {
                    System.out.println("Kullanici yok.");
                }

                if (projects.isEmpty()) {
                    System.out.println("Proje yok.");
                    continue;
                }

                System.out.println("\n--- Projeler ---");
                for (Project p : projects) {
                    System.out.println(p.getId() + " - " + p.getName() + " (Task: " + p.getTasks().size() + ")");
                }

                Project selectedProject = selectProjectById(sc, projects);

                System.out.println("\n--- Proje Detayi ---");
                System.out.println("Proje adi: " + selectedProject.getName());
                System.out.println("Toplam gorev sayisi: " + selectedProject.getTasks().size());

                System.out.println("\n--- Tum Gorevler ---");
                for (Task t : selectedProject.getTasks()) {
                    System.out.println(t);
                }

                System.out.println("\n--- Tamamlanmamis Gorevler ---");
                for (Task t : selectedProject.getIncompleteTasks()) {
                    System.out.println(t);
                }
            }

            else if (choice == 9) {
                // 9) Export
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                System.out.print("Export dosya adi girin: ");
                String fileName = sc.nextLine();

                if (fileName.isEmpty()) {
                    fileName = "project_export.txt";
                } else if (!fileName.endsWith(".txt")) {
                    fileName = fileName + ".txt";
                }

                FileExporter.exportProjectToTxt(selectedProject, fileName);
                System.out.println("Export done: " + fileName + " (Proje: " + selectedProject.getName() + ")");
            }

            else if (choice == 10) {
                // 10) Gorev sil (Task sil)
                if (projects.isEmpty()) {
                    System.out.println("Once proje olusturun.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                if (selectedProject.getTasks().isEmpty()) {
                    System.out.println("Bu projede task yok.");
                    continue;
                }

                System.out.println("\n--- Gorevler (Proje: " + selectedProject.getName() + ") ---");
                for (Task t : selectedProject.getTasks()) {
                    System.out.println("ID: " + t.getId() + " -> " + t);
                }

                int taskId = readNumberOnly(sc, "Silinecek task ID: ");
                boolean removed = selectedProject.removeTaskById(taskId);

                if (removed) {
                    System.out.println("Task silindi: " + taskId);
                } else {
                    System.out.println("Task bulunamadi.");
                }
            }

            else if (choice == 11) {
                // 11) Proje sil
                if (projects.isEmpty()) {
                    System.out.println("Proje yok.");
                    continue;
                }

                Project selectedProject = selectProjectById(sc, projects);

                System.out.print("Bu projeyi silmek istiyor musunuz? (E/H): ");
                String ans = sc.nextLine().trim().toUpperCase();

                if (!ans.equals("E")) {
                    System.out.println("Iptal edildi.");
                    continue;
                }

                boolean removed = projects.remove(selectedProject);
                if (removed) {
                    System.out.println("Proje silindi: " + selectedProject.getId() + " - " + selectedProject.getName());
                } else {
                    System.out.println("Proje silinemedi.");
                }
            }
        }

        System.out.println("\n=== Demo Bitti ===");
        sc.close();
    }

    private static int readNumberOnly(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Harf veya gecersiz karakter girilemez.");
        }
    }

    private static Priority readPriority(Scanner sc) {
        while (true) {
            System.out.print("Priority girin (LOW/MEDIUM/HIGH): ");
            String p = sc.nextLine().trim().toUpperCase();
            try {
                return Priority.valueOf(p);
            } catch (Exception e) {
                System.out.println("Hatali priority.");
            }
        }
    }

    private static User selectUserById(Scanner sc, List<User> users) {
        while (true) {
            System.out.println("Kullanicilar:");
            for (User u : users) {
                System.out.println(u.getId() + " - " + u.getName());
            }

            int id = readNumberOnly(sc, "Kullanici ID secin: ");
            for (User u : users) {
                if (u.getId() == id) {
                    return u;
                }
            }

            System.out.println("Gecersiz kullanici ID.");
        }
    }

    private static Project selectProjectById(Scanner sc, List<Project> projects) {
        while (true) {
            System.out.println("Projeler:");
            for (Project p : projects) {
                System.out.println(p.getId() + " - " + p.getName());
            }

            int id = readNumberOnly(sc, "Proje ID secin: ");
            for (Project p : projects) {
                if (p.getId() == id) {
                    return p;
                }
            }

            System.out.println("Gecersiz proje ID.");
        }
    }
}
