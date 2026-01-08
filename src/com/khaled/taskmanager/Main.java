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

	    User user = null;
	    Project project = null;

	    List<User> users = new ArrayList<>();

	    while (true) {

	        System.out.println("\n1) Kullanici olustur");
	        System.out.println("2) Proje olustur");
	        System.out.println("3) Task olustur (priority + deadline)");
	        System.out.println("4) Task olustur (default priority) + deadline");
	        System.out.println("5) TimedTask olustur (inheritance)");
	        System.out.println("7) Yaklasan gorevleri listele (artik project dolu)");
	        System.out.println("8) Bir gorevi tamamla");
	        System.out.println("9) Yaklasan deadline icin bildirim (Notification) testi");
	        System.out.println("10) Ciktilar");
	        System.out.println("11) Export");
	        System.out.println("0) Cikis");

	        int choice = readNumberOnly(sc, "Secim: ");

	        if (choice == 0) break;

	        if (choice == 1) {
	            // 1) Kullanici olustur
	            System.out.print("Kullanici adi girin: ");
	            String userName = sc.nextLine();
	            User newUser = new User(nextUserId++, userName);
	            users.add(newUser);
	            user = newUser;
	            System.out.println("User olusturuldu: " + newUser.getId() + " - " + newUser.getName());
	        }

	        else if (choice == 2) {
	            // 2) Proje olustur ve kullaniciya ekle
	            System.out.print("Proje adi girin: ");
	            String projectName = sc.nextLine();

	            project = new Project(nextProjectId++, projectName);

	            System.out.println("Proje olusturuldu: " + project.getName());
	        }

	        else if (choice == 3) {
	            // 3) Task olustur (priority + deadline)
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            if (users.isEmpty()) {
	                System.out.println("Once kullanici olusturun.");
	                continue;
	            }

	            User assignedUser = selectUserById(sc, users);

	            System.out.print("Task baslik girin: ");
	            String title = sc.nextLine();
	            System.out.print("Task aciklama girin: ");
	            String desc = sc.nextLine();
	            Priority pr = readPriority(sc);
	            int days = readNumberOnly(sc, "Deadline kac gun sonra? ");

	            Task task1 = new Task(nextTaskId++, title, desc, pr);
	            task1.setDeadline(new Deadline(LocalDate.now().plusDays(days)));
	            task1.setAssignedUserName(assignedUser.getName());

	            project.addTask(task1);
	            System.out.println("Task eklendi: " + task1.getId());
	        }

	        else if (choice == 4) {
	            // 4) Task olustur (default priority) + deadline
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            if (users.isEmpty()) {
	                System.out.println("Once kullanici olusturun.");
	                continue;
	            }

	            User assignedUser = selectUserById(sc, users);

	            System.out.print("Task baslik girin: ");
	            String title = sc.nextLine();
	            System.out.print("Task aciklama girin: ");
	            String desc = sc.nextLine();
	            Priority pr = readPriority(sc);
	            int days = readNumberOnly(sc, "Deadline kac gun sonra? ");

	            Task task2 = new Task(nextTaskId++, title, desc);
	            task2.setPriority(pr);
	            task2.setDeadline(new Deadline(LocalDate.now().plusDays(days)));
	            task2.setAssignedUserName(assignedUser.getName());

	            project.addTask(task2);
	            System.out.println("Task eklendi: " + task2.getId());
	        }

	        else if (choice == 5) {
	            // 5) TimedTask olustur (inheritance)
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            if (users.isEmpty()) {
	                System.out.println("Once kullanici olusturun.");
	                continue;
	            }

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

	            project.addTask(timedTask);
	            System.out.println("TimedTask eklendi: " + timedTask.getId());
	        }
	        else if (choice == 7) {
	            // 7) Yaklasan gorevleri listele (artik project dolu)
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            System.out.println("\n--- Yaklasan Gorevler ---");
	            for (Task t : project.getApproachingTasks()) {
	                System.out.println(t);
	            }
	        }

	        else if (choice == 8) {
	            // 8) Bir gorevi tamamla
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            int taskId = readNumberOnly(sc, "Tamamlanacak task ID: ");
	            boolean found = false;
	            for (Task t : project.getTasks()) {
	                if (t.getId() == taskId) {
	                    t.markCompleted();
	                    found = true;
	                    System.out.println("Task tamamlandi: " + taskId);
	                    break;
	                }
	            }
	            if (!found) {
	                System.out.println("Task bulunamadi.");
	            }
	        }

	        else if (choice == 9) {
	            // 9) Yaklasan deadline icin bildirim (Notification) testi
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            int taskId = readNumberOnly(sc, "Bildirim icin task ID: ");
	            Task selected = null;
	            for (Task t : project.getTasks()) {
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

	        else if (choice == 10) {
	            // 10) Ciktilar
	            if (users.isEmpty()) {
	                System.out.println("Kullanici yok.");
	                continue;
	            }

	            System.out.println("\n--- Kullanicilar ---");
	            for (User u : users) {
	                System.out.println(u.getId() + " - " + u.getName());
	            }

	            if (project != null) {
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
	            }
	        }

	        else if (choice == 11) {
	            // 11) Export
	            if (project == null) {
	                System.out.println("Once proje olusturun.");
	                continue;
	            }
	            System.out.print("Export dosya adi girin: ");
	            String fileName = sc.nextLine();

	            if (fileName.isEmpty()) {
	                fileName = "project_export.txt";
	            } else if (!fileName.endsWith(".txt")) {
	                fileName = fileName + ".txt";
	            }

	            FileExporter.exportProjectToTxt(project, fileName);
	            System.out.println("Export done: " + fileName);

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
}
