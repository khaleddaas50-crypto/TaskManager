package com.khaled.taskmanager;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.khaled.taskmanager.model.Deadline;
import com.khaled.taskmanager.model.Task;

public class TaskTest {

    @Test
    void newTask_shouldBeNotCompleted() {
        Task t = new Task(1, "Test", "Desc");
        assertFalse(t.isCompleted());
    }

    @Test
    void markCompleted_shouldSetCompletedTrue() {
        Task t = new Task(1, "Test", "Desc");
        t.markCompleted();
        assertTrue(t.isCompleted());
    }

    @Test
    void deadlineApproaching_shouldReturnTrue() {
        Task t = new Task(1, "Test", "Desc");
        t.setDeadline(new Deadline(LocalDate.now().plusDays(2)));
        assertTrue(t.getDeadline().isApproaching());
    }

    @Test
    void deadlineFar_shouldReturnFalse() {
        Task t = new Task(1, "Test", "Desc");
        t.setDeadline(new Deadline(LocalDate.now().plusDays(10)));
        assertFalse(t.getDeadline().isApproaching());
    }
}
