package org.example.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TaskManagerTest {
    @Test
    void CheckIfTaskManagerIsInitialized() {
        TaskManager taskManager = new TaskManager();
        assertNotNull(taskManager);
    }

    @Test
    void CheckIfTaskIsRemoved() {
        Task task = new Task();
        task.set_title("Task Title");
        task.set_isCompleted(true);

        TaskManager taskManager = new TaskManager();
        task.set_taskMap(new HashMap<>(Map.of(
                "Task Title", new ArrayList<>(List.of(task))
        )));

        boolean isRemoved = taskManager.RemoveTask(task.get_title());

        assertTrue(isRemoved);
    }

    @Test
    void CheckIfTaskIsCompleted() {
        Task task = new Task();
        task.set_title("Task Title");
        task.set_description("Description Title");
        task.set_isCompleted(false);

        TaskManager taskManager = new TaskManager();
        task.set_taskMap(new HashMap<>(Map.of(
                "Task Title", new ArrayList<>(List.of(task))
        )));

        boolean isCompleted = taskManager.CompleteTask(task.get_title(), task.get_description());

        assertTrue(isCompleted);
    }
}