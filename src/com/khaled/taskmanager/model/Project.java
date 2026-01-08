package com.khaled.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Project sinifi birden fazla gorevi yonetir.
 * Gorev ekleme, silme ve listeleme islemlerini saglar.
 */
public class Project {

    private int id;
    private String name;
    private List<Task> tasks;

    public Project(int id, String name) {
        validate(id, name);
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    /**
     * Project nesnesi olusturmak icin kullanilir.
     */
    public static Project create(int id, String name) {
        return new Project(id, name);
    }
    /**
     * Project icin temel dogrulamalari yapar.
     */
    private static void validate(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Project id must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
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

    /**
     * Projeye yeni bir gorev ekler.
     *
     * @param task eklenecek gorev
     */
    public void addTask(Task task) {
        // (Opsiyonel) null kontrolu:
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(task);
    }

    /**
     * Tamamlanmamis gorevleri listeler.
     *
     * @return tamamlanmamis gorevlerin listesi
     */
    public List<Task> getIncompleteTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Deadline'i yaklasan gorevleri listeler.
     *
     * @return yaklasan gorevlerin listesi
     */
    public List<Task> getApproachingTasks() {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeadline() != null && task.getDeadline().isApproaching()) {
                result.add(task);
            }
        }

        return result;
    }

    /**
     * Verilen ID'ye sahip gorevi projeden siler.
     *
     * @param taskId silinecek gorevin ID'si
     * @return true eger gorev silindiyse
     **/
    public boolean removeTaskById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Verilen ID'ye sahip gorevi bulur.
     *
     * @param taskId aranacak gorevin ID'si
     * @return Task eger bulunursa, aksi halde null
     */
    public Task getTaskById(int taskId) {
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                return t;
            }
        }
        return null;
    }

    /**
     * Projede verilen ID'ye sahip gorev var mi?
     *
     * @param taskId kontrol edilecek gorev ID
     * @return true eger varsa
     */
    public boolean containsTaskId(int taskId) {
        return getTaskById(taskId) != null;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskCount=" + (tasks != null ? tasks.size() : 0) +
                '}';
    }
}
