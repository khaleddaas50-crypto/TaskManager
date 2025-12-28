package com.khaled.taskmanager;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.khaled.taskmanager.model.Project;
import com.khaled.taskmanager.model.Task;
import com.khaled.taskmanager.model.Deadline;

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


@Test
void getIncompleteTasks_shouldReturnOnlyIncompleteTasks() {
    Project project = new Project(1, "Test Project");

    Task t1 = new Task(1, "Task 1", "Desc");
    Task t2 = new Task(2, "Task 2", "Desc");

    project.addTask(t1);
    project.addTask(t2);

    // t1 tamamlandi
    t1.markCompleted();

    // sadece t2 incomplete olmali
    assertEquals(1, project.getIncompleteTasks().size());
    assertEquals(2, project.getIncompleteTasks().get(0).getId());
    }
    @Test
    void getApproachingTasks_shouldReturnOnlyApproachingTasks() {
        Project project = new Project(1, "Test Project");

        Task t1 = new Task(1, "Task 1", "Desc");
        t1.setDeadline(new Deadline(LocalDate.now().plusDays(1))); // قريب

        Task t2 = new Task(2, "Task 2", "Desc");
        t2.setDeadline(new Deadline(LocalDate.now().plusDays(10))); // مو قريب

        Task t3 = new Task(3, "Task 3", "Desc"); // بدون deadline

        project.addTask(t1);
        project.addTask(t2);
        project.addTask(t3);

        assertEquals(1, project.getApproachingTasks().size());
        assertEquals(1, project.getApproachingTasks().get(0).getId());
    

    }
}
