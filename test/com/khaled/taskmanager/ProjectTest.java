package com.khaled.taskmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.khaled.taskmanager.model.Project;
import com.khaled.taskmanager.model.Task;

public class ProjectTest {

	@Test
	void addTask_shouldIncreaseTaskCount() {
	    Project project = new Project(1, "Test Project");

	    assertEquals(0, project.getTasks().size()); // optional
	    project.addTask(new Task(1, "Task 1", "Desc"));
	    assertEquals(1, project.getTasks().size());
	}


    @Test
    void removeTaskById_shouldRemoveCorrectTask() {
        Project project = new Project(1, "Test Project");

        Task task1 = new Task(1, "Task 1", "Desc");
        Task task2 = new Task(2, "Task 2", "Desc");

        project.addTask(task1);
        project.addTask(task2);

        boolean removed = project.removeTaskById(1);

        assertTrue(removed);
        assertEquals(1, project.getTasks().size());
        assertEquals(2, project.getTasks().get(0).getId());
    }
}
