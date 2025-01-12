package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorkTaskTest {

    private WorkTask workTask;

    @Test
    void CheckIfTitleIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, null, false, "Test");
        assertEquals("Test", workTask.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, null, false, "Test");
        assertEquals("Test", workTask.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, null, false, "Test");
        assertEquals(1, workTask.get_priority());
    }

    @Test
    void CheckIfDateIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, LocalDate.now(), false, "Test");
        assertEquals(LocalDate.now(), workTask.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, null, false, "Test");
        assertFalse(workTask.get_isCompleted());
    }

    @Test
    void CheckIfWorkSpaceIsCorrect(){
        WorkTask workTask = new WorkTask("Test", "Test", 1, null, false, "Test");
        assertEquals("Test", workTask.get_workSpace());
    }

    @Test
    void CheckIfTitleIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_title("Test");
        assertEquals("Test", task.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_description("Test");
        assertEquals("Test", task.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_priority(1);
        assertEquals(1, task.get_priority());
    }

    @Test
    void CheckIfDeadlineIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_deadline(LocalDate.now());
        assertEquals(LocalDate.now(), task.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_isCompleted(true);
        assertTrue(task.get_isCompleted());
    }

    @Test
    void CheckIfWorkSpaceIsCorrectAfterSet(){
        WorkTask task = new WorkTask();
        task.set_workSpace("Test");
        assertEquals("Test", task.get_workSpace());
    }

    @BeforeEach
    void setUp() {
        workTask = new WorkTask(
                "Task1",
                "Description1",
                1,
                LocalDate.of(2025, 10, 20),
                false,
                "Departament IT"
        );

        workTask.set_taskMap(new HashMap<>(Map.of(
                "Task1", new ArrayList<>(List.of(workTask))
        )));
    }

    @Test
    void PrintTaskTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            workTask.PrintTask("Task1", "Description1");
        } finally {
            System.setOut(System.out);
        }

        String expectedOutput = """
            Titlu: Task1
            Descriere: Description1
            Prioritate: 1
            Termen limita: 2025-10-20
            Este completat: false
            Spatiu de lucru: Departament IT
            """.trim();

        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();
        String normalizedExpectedOutput = expectedOutput.replace("\r\n", "\n").trim();

        assertEquals(normalizedExpectedOutput, actualOutput);
    }
}