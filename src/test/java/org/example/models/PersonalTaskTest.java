package org.example.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PersonalTaskTest {

    private PersonalTask personalTask;

    @Test
    void CheckIfTitleIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, false, null, null);
        assertEquals("Test", task.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, false, null, null);
        assertEquals("Test", task.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, false, null, null);
        assertEquals(1, task.get_priority());
    }

    @Test
    void CheckIfDeadlineIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, LocalDate.now(), false, null, null);
        assertEquals(LocalDate.now(), task.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, true, null, null);
        assertTrue(task.get_isCompleted());
    }

    @Test
    void CheckIfLocationIsCorrect(){
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, false, "Test", null);
        assertEquals("Test", task.get_location());
    }

    @Test
    void CheckIfRelatedPeopleIsCorrect(){
        ArrayList<String> relatedPeople = new ArrayList<>();
        relatedPeople.add("Person1");
        relatedPeople.add("Person2");
        relatedPeople.add("Person3");
        PersonalTask task = new PersonalTask("Test", "Test", 1, null, false, null, relatedPeople);

        for(String person : task.get_relatedPeople()) {
            assertTrue(relatedPeople.contains(person));
        }
    }

    @Test
    void CheckIfTitleIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_title("Test");
        assertEquals("Test", task.get_title());
    }

    @Test
    void CheckIfDescriptionIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_description("Test");
        assertEquals("Test", task.get_description());
    }

    @Test
    void CheckIfPriorityIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_priority(1);
        assertEquals(1, task.get_priority());
    }

    @Test
    void CheckIfDeadlineIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_deadline(LocalDate.now());
        assertEquals(LocalDate.now(), task.get_deadline());
    }

    @Test
    void CheckIfIsCompletedIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_isCompleted(true);
        assertTrue(task.get_isCompleted());
    }

    @Test
    void CheckIfLocationIsCorrectAfterSet(){
        PersonalTask task = new PersonalTask();
        task.set_location("Test");
        assertEquals("Test", task.get_location());
    }

    @Test
    void CheckIfRelatedPeopleIsCorrectAfterSet(){
        ArrayList<String> relatedPeople = new ArrayList<>();
        relatedPeople.add("Person1");
        relatedPeople.add("Person2");
        relatedPeople.add("Person3");
        PersonalTask task = new PersonalTask();
        task.set_relatedPeople(relatedPeople);

        for(String person : task.get_relatedPeople()) {
            assertTrue(relatedPeople.contains(person));
        }
    }

    @Test
    void CheckIsRelatedTofunction(){
        PersonalTask task = new PersonalTask();

        String person = "Person1";

        ArrayList<String> relatedPeople = new ArrayList<>();
        relatedPeople.add("Person1");

        task.set_relatedPeople(relatedPeople);

        assertTrue(task.isRelatedTo(person));
    }

    @BeforeEach
    void setUp() {
        ArrayList<String> relatedPeople = new ArrayList<>();
        relatedPeople.add("Flaviu");
        relatedPeople.add("Petre");

        personalTask = new PersonalTask(
                "Task1",
                "Description1",
                1,
                LocalDate.of(2025, 10, 20),
                false,
                "Brasov",
                relatedPeople
        );

        // Mock the get_tasks() method by overriding it
        personalTask.set_taskMap(new HashMap<>(Map.of(
                "Task1", new ArrayList<>(List.of(personalTask))
        )));
    }

    @Test
    void PrintTaskTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            personalTask.PrintTask("Task1", "Description1");
        } finally {
            System.setOut(System.out);
        }

        String expectedOutput = """
            Titlu: Task1
            Descriere: Description1
            Prioritate: 1
            Termen limita: 2025-10-20
            Este completat: false
            Locatie: Brasov
            Persoane implicate:\s
            Flaviu
            Petre
            """.trim();

        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();
        String normalizedExpectedOutput = expectedOutput.replace("\r\n", "\n").trim();

        assertEquals(normalizedExpectedOutput, actualOutput);
    }
}