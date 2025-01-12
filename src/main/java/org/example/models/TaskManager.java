package org.example.models;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.interfaces.ITaskManager;

public class TaskManager implements ITaskManager {
    //region Variables
    @JsonIgnore
    private static Map<String, ArrayList<Task>> _taskMap = new HashMap<>();
    //endregion

    //region Constructors
    public TaskManager(){}
    public TaskManager(Map<String, ArrayList<Task>> _taskMap) {this._taskMap = _taskMap;}
    //endregion

    //region Methodes
    @Override
    public void AddTask(Task newTask) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Titlu: ");
        String title = scanner.nextLine();
        newTask.set_title(title);

        System.out.print("Descriere: ");
        String description = scanner.nextLine();
        newTask.set_description(description);

        System.out.print("Prioritate (0 - 5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        if(priority >=0 && priority <= 5)
            newTask.set_priority(priority);
        else{
            while(priority < 0 || priority > 5) {
                System.out.print("Prioritate (0 - 5): ");
                priority = scanner.nextInt();
            }
            newTask.set_priority(priority);
        }

        System.out.print("Termenul limită (yyyy-mm-dd): ");
        String deadlineInput = scanner.nextLine();
        LocalDate deadline = LocalDate.parse(deadlineInput);
        newTask.set_deadline(deadline);

        boolean isCompleted = false;
        newTask.set_isCompleted(isCompleted);

        _taskMap.putIfAbsent(title, new ArrayList<>());
        _taskMap.get(title).add(newTask);
    }

    @Override
    public boolean RemoveTask(String title) {
        if(_taskMap.isEmpty()) {
            return false;
        }

        ArrayList<Task> taskList = _taskMap.get(title);
        if(taskList != null) {
            for(Task task : taskList) {
                if(task.get_isCompleted()) {
                    taskList.remove(task);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void PrintTask(String title, String description) {
        Map<String, ArrayList<Task>> taskMap = get_tasks();
        for(Task task : taskMap.get(title)) {
            if(task.get_description().equals(description)) {
                System.out.println("Titlu: " + task.get_title());
                System.out.println("Descriere: " + task.get_description());
                System.out.println("Prioritate: " + task.get_priority());
                System.out.println("Termen limita: " + task.get_deadline());
                System.out.println("Este completat: " + task.get_isCompleted() + "\n");
            }
        }

    }

    @Override
    public boolean CompleteTask(String title, String description) {
        ArrayList<Task> taskList = _taskMap.get(title);
        if(taskList != null) {
            for(Task task : taskList) {
                if(task.get_description().equals(description)) {
                    if(!task.get_isCompleted()) {
                        task.set_isCompleted(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean IsDeadlineExpired(String title, String description) {
        ArrayList<Task> taskList = _taskMap.get(title);
        if(taskList != null) {
            for(Task task : taskList) {
                if(task.get_description().equals(description)) {
                    if(task.get_deadline().isBefore(LocalDate.now()))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void sortTasksByPriority(){
        List<Task> allTasks = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Task>> entry : _taskMap.entrySet()) {
            allTasks.addAll(entry.getValue());
        }

        allTasks.sort(Comparator.comparingInt(Task::get_priority).reversed());

        for (Task task : allTasks) {
            System.out.println(task.get_description() + " " + task.get_priority());
        }
    }

    @Override
    public void saveToFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            objectMapper.writeValue(new File(filePath), this._taskMap);
            System.out.println("Task-ul a fost salvat in fișierul " + "tasks.json");
        } catch (IOException e) {
            System.err.println("Eroare la salvarea task-ului " + e.getMessage());
        }
    }

    @Override
    public Map<String, ArrayList<Task>> readFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<Map<String, ArrayList<Task>>>() {});
        } catch (IOException e) {
            System.out.println("Failed to read tasks: " + e.getMessage());
            return null;
        }
    }
    //endregion

    //region Getters
    @JsonIgnore
    public Map<String, ArrayList<Task>> get_tasks() {
        return _taskMap;
    }
    //endregion

    //region Setters
    public void set_taskMap(Map<String, ArrayList<Task>> _taskMap) {
        this._taskMap = _taskMap;
    }
    //endregion

}
