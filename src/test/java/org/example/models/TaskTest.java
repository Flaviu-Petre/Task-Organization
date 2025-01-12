package org.example.models;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void CheckIfTitleIsCorrect() {
        Task task = new Task("Test", "Test", 1, null, false);
        assertEquals("Test", task.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrect() {
        Task task = new Task("Test", "Test", 1, null, false);
        assertEquals("Test", task.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrect() {
        Task task = new Task("Test", "Test", 1, null, false);
        assertEquals(1, task.get_priority());
    }

    @Test
    void CheckIfDeadlineIsCorrect() {
        Task task = new Task("Test", "Test", 1, LocalDate.now(), false);
        assertEquals(LocalDate.now(), task.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrect() {
        Task task = new Task("Test", "Test", 1, null, false);
        assertFalse(task.get_isCompleted());
    }

    @Test
    void CheckIfTitleIsCorrectAfterSet() {
        Task task = new Task();
        task.set_title("Test");
        assertEquals("Test", task.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrectAfterSet() {
        Task task = new Task();
        task.set_description("Test");
        assertEquals("Test", task.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrectAfterSet() {
        Task task = new Task();
        task.set_priority(1);
        assertEquals(1, task.get_priority());
    }

    @Test
    void CheckIfDeadlineIsCorrectAfterSet() {
        Task task = new Task();
        task.set_deadline(LocalDate.now());
        assertEquals(LocalDate.now(), task.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrectAfterSet() {
        Task task = new Task();
        task.set_isCompleted(true);
        assertTrue(task.get_isCompleted());
    }
}