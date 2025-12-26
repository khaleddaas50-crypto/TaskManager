package com.khaled.taskmanager.model;
import java.util.ArrayList;
import java.util.List;
/**
 * User sinifi sistemi kullanan kullaniciyi temsil eder.
 * Kullaniciya ait projeleri tutar.
 */

public class User {

    private int id;
    private String name;
    private List<Project> projects;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.projects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectCount=" + (projects != null ? projects.size() : 0) +
                '}';
    }

}
