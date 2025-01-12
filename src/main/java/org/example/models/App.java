package org.example.models;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import org.example.models.Task;

public class App extends Task{
    //region Variables
    private char _option;
    private Scanner _scanner;
    //endregion

    //region Constructors
    public App() {}
    public App(char option, Scanner scanner) {
        _option = option;
        _scanner = scanner;
    }
    //endregion

    //region Methodes
    public void StartApp() {
        _scanner = new Scanner(System.in);
        System.out.println("Bine ai venit în aplicația de organizare a task-urilor!");
        System.out.println("Alege o opțiune:");
        System.out.println("1. Adaugă un task.");
        System.out.println("2. Afișează task-urile.");
        System.out.println("3. Șterge un task.");
        System.out.println("4. Setează task finalizat");
        System.out.println("5. Verifică dacă termenul limită al unui task a expirat.");
        System.out.println("6. Sortează task-urile după prioritatea");
        System.out.println("7. Verifică dacă o persoană este implicată într-un task.");
        System.out.println("8. Afișează task-urile care sunt urgente în cadrul locului de muncă.");
        System.out.println("9. Ieșire.");
    }

    public void Navigate(TaskManager taskManager){
        _option = '\u0000';
        while (_option != '9') {
            _option = _scanner.next().charAt(0);
            switch (_option) {
                case '1': {
                    char option = '\u0000';
                    System.out.println("Ce tip de task dorești să adaugi?");
                    System.out.println("1. Task simplu");
                    System.out.println("2. Task personal");
                    System.out.println("3. Task muncă");
                    option = get_scanner().next().charAt(0);
                    get_scanner().nextLine();
                    switch (option) {
                        case '1':{
                            Task task = new Task();
                            taskManager.AddTask(task);
                            taskManager.saveToFile("src/main/resources/tasks.json");
                            break;
                        }
                        case '2': {
                            PersonalTask personalTask = new PersonalTask();

                            System.out.print("Locația: ");
                            String location = get_scanner().nextLine();
                            personalTask.set_location(location);

                            System.out.print("Persoane: ");
                            ArrayList<String> relatedPeople = new ArrayList<>();
                            while (true) {
                                String input = get_scanner().nextLine();
                                if ("exit".equalsIgnoreCase(input)) {
                                    break;
                                }
                                relatedPeople.add(input);
                            }
                            personalTask.set_relatedPeople(relatedPeople);

                            taskManager.AddTask(personalTask);
                            taskManager.saveToFile("src/main/resources/tasks.json");
                            break;
                        }
                        case '3': {
                            WorkTask workTask = new WorkTask();

                            System.out.print("Loc de muncă: ");
                            String workSpace = get_scanner().nextLine();
                            workTask.set_workSpace(workSpace);

                            taskManager.AddTask(workTask);
                            taskManager.saveToFile("src/main/resources/tasks.json");
                            break;
                        }
                        default:{
                            System.out.println("Selectează o opțiune validă!");
                            break;
                        }
                    }
                    System.out.println("Task adaugat");
                    break;
                }
                case '2': {
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();
                    if(tasks.isEmpty()){
                        System.out.println("Nu există niciun task!");
                        break;
                    }
                    for (Map.Entry<String, ArrayList<Task>> entry : tasks.entrySet()) {
                        for (Task task : entry.getValue()) {
                            if(task instanceof PersonalTask){
                                PersonalTask personalTask = (PersonalTask) task;
                                personalTask.PrintTask(task.get_title(), task.get_description());
                            }
                            else if(task instanceof WorkTask){
                                WorkTask workTask = (WorkTask) task;
                                workTask.PrintTask(task.get_title(), task.get_description());
                            }
                            else
                                task.PrintTask(task.get_title(), task.get_description());
                        }
                    }
                    break;
                }
                case '3': {
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();
                    System.out.println("Ce task doriți să ștergeti?");

                    get_scanner().nextLine();
                    System.out.print("Titlu: ");
                    String title = get_scanner().nextLine();
                    boolean isCompleted = taskManager.RemoveTask(title);

                    if(isCompleted){
                        taskManager.saveToFile("src/main/resources/tasks.json");
                        System.out.println("Task șters");
                    }
                    else
                        System.out.println("Task-ul nu a fost sters!");
                    break;
                }
                case '4':{
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();;
                    System.out.println("Ce task doriți să finalizați?");

                    get_scanner().nextLine();
                    System.out.print("Titlu: ");
                    String title = get_scanner().nextLine();


                    System.out.print("Descriere: ");
                    String description = get_scanner().nextLine();

                    boolean isCompleted = taskManager.CompleteTask(title, description);

                    if(isCompleted){
                        taskManager.saveToFile("src/main/resources/tasks.json");
                        System.out.println("Task finalizat");
                    }
                    else
                        System.out.println("Task-ul nu a fost finalizat cu succes!");
                    break;
                }
                case '5':{
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();
                    System.out.println("Ce task doriți să verificați?");

                    get_scanner().nextLine();
                    System.out.print("Titlu: ");
                    String title = get_scanner().nextLine();

                    System.out.print("Descriere: ");
                    String description = get_scanner().nextLine();

                    boolean isExpired = taskManager.IsDeadlineExpired(title, description);

                    if(isExpired){
                        ArrayList<Task> taskList = tasks.get(title);
                        for(Task task : taskList){
                            if(task.get_description().equals(description)){
                                taskList.remove(task);
                                break;
                            }
                        }
                        System.out.println("Termenul limită task-ului a expirat!");
                        taskManager.saveToFile("src/main/resources/tasks.json");
                    }
                    else
                        System.out.println("Termenul limită al tasktask-ului nu a expirat!");
                    break;
                }
                case '6':{
                    taskManager.sortTasksByPriority();
                    break;
                }
                case '7':{
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();
                    System.out.println("Ce task persoană doriți să verificați?");
                    get_scanner().nextLine();
                    System.out.print("Nume: ");
                    String name = get_scanner().nextLine();

                    for (Map.Entry<String, ArrayList<Task>> entry : tasks.entrySet()){
                        for(Task task : entry.getValue()){
                            if(task instanceof PersonalTask){
                                PersonalTask personalTask = (PersonalTask) task;
                                for(String person : personalTask.get_relatedPeople()){
                                    if(person.equals(name)){
                                        System.out.println(name + " este implicat în task-ul " + entry.getKey());
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case '8':{
                    Map<String, ArrayList<Task>> tasks = taskManager.get_tasks();
                    for (Map.Entry<String, ArrayList<Task>> entry : tasks.entrySet()){
                        for(Task task : entry.getValue()){
                            if(task instanceof WorkTask){
                                WorkTask workTask = (WorkTask) task;
                                if(workTask.isUrgent()){
                                    System.out.println("Task-ul " + entry.getKey() + " este urgent!");
                                    System.out.println("Zile rămase până la termenul limită: " + workTask.getEstimatedCompletionTime());
                                }
                            }
                        }
                    }
                    break;
                }
                case '9': {
                    System.out.println("La revedere!");
                    break;
                }
                default: {
                    System.out.println("Selectează o opțiune validă!");
                    break;
                }
            }
        }
    }
    //endregion

    //region Getters
    public char get_option() {
        return _option;
    }
    public Scanner get_scanner() {
        return _scanner;
    }
    //endregion

    //region Setters
    public void set_option(char _option) {
        this._option = _option;
    }
    public void set_scanner(Scanner _scanner) {
        this._scanner = _scanner;
    }
    //endregion
}
