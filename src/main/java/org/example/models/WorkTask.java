package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@JsonTypeName("work")
public class WorkTask extends Task{
    //region Variables
    private String _workSpace;
    private final String _type = "work";
    //endregion

    //region Constructors
    public WorkTask() {}
    public WorkTask(String title, String description, int priority, LocalDate deadline, boolean isCompleted, String _workSpace) {
        super(title, description, priority, deadline, isCompleted);
        this._workSpace = _workSpace;
    }
    //endregion

    //region Methodes
    @JsonIgnore
    public boolean isUrgent() {
        return get_priority() >= 4;
    }

    @JsonIgnore
    public long getEstimatedCompletionTime() {
        if (get_deadline() == null) return -1;
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), get_deadline());
    }

    @Override
    public void PrintTask(String title, String description) {
        Map<String, ArrayList<Task>> taskMap = get_tasks();
        for (Task task : taskMap.get(title)) {
            if (task.get_description().equals(description)) {
                System.out.println("Titlu: " + task.get_title());
                System.out.println("Descriere: " + task.get_description());
                System.out.println("Prioritate: " + task.get_priority());
                System.out.println("Termen limita: " + task.get_deadline());
                System.out.println("Este completat: " + task.get_isCompleted());
                System.out.println("Spatiu de lucru: " + get_workSpace() + "\n");
            }
        }
    }
    //endregion

    //region Getters
    public String get_type() { return _type; }
    public String get_workSpace() {
        return _workSpace;
    }
    //endregion

    //region Setters
    public void set_workSpace(String _workSpace) {
        this._workSpace = _workSpace;
    }
    //endregion

}
