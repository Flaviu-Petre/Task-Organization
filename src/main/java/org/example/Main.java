package org.example;
import org.example.models.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if(taskManager.readFromFile("src/main/resources/tasks.json") != null)
            taskManager.set_taskMap(taskManager.readFromFile("src/main/resources/tasks.json"));

        App app = new App();
        app.StartApp();
        app.Navigate(taskManager);
    }
}