package com.khaled.taskmanager.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileExporter {

    // Proje ve görevlerini basit bir txt dosyasına yazar
    public static void exportProjectToTxt(Project project, String filePath) {
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
        }
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath cannot be empty");
        }

        List<String> lines = new ArrayList<>();
        lines.add("Project: " + project.getName() + " (id=" + project.getId() + ")");
        lines.add("Task count: " + project.getTasks().size());
        lines.add("----------------------------------");

        for (Task t : project.getTasks()) {
            lines.add(t.toString());
        }

        try {
            Files.write(Path.of(filePath), lines);
        } catch (IOException e) {
            throw new RuntimeException("File write failed: " + filePath, e);
        }
    }
}
