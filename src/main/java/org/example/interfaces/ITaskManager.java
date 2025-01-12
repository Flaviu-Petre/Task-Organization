package org.example.interfaces;

import org.example.models.Task;

import java.util.ArrayList;
import java.util.Map;

public interface ITaskManager {
    void AddTask(Task newTask);
    boolean RemoveTask(String title);
    void PrintTask(String title, String description);
    boolean CompleteTask(String title, String description);
    boolean IsDeadlineExpired(String title, String description);
    void sortTasksByPriority();
    void saveToFile(String filePath);
    Map<String, ArrayList<Task>> readFromFile(String filePath);

}
