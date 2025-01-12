package org.example.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonTypeName("personal")
public class PersonalTask extends Task{
    //region Variables
    private String _location;
    private ArrayList<String> _relatedPeople;
    private final String _type = "personal";
    // endregion

    //region Constructors
    public PersonalTask() {}
    public PersonalTask(String title, String description, int priority, LocalDate deadline, boolean isCompleted, String _location, ArrayList<String> _relatedPeople) {
        super(title, description, priority, deadline, isCompleted);
        this._location = _location;
        this._relatedPeople = _relatedPeople;
    }
    //endregion

    //region Methodes
    public boolean isRelatedTo(String person) {
        return _relatedPeople != null && _relatedPeople.contains(person);
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
                System.out.println("Este completat: " + task.get_isCompleted());
                System.out.println("Locatie: " + get_location());
                System.out.println("Persoane implicate: ");
                for(String person : get_relatedPeople()) {
                    System.out.println(person);
                }
                System.out.print("\n");
            }
        }

    }
    //endregion

    //region Getters
    public String get_type() {
        return _type;
    }

    public String get_location() {
        return _location;
    }

    public List<String> get_relatedPeople() {
        return _relatedPeople;
    }

    //endregion

    //region Setters
    public void set_location(String _location) {
        this._location = _location;
    }

    public void set_relatedPeople(ArrayList<String> _relatedPeople) {
        this._relatedPeople = _relatedPeople;
    }
    //endregion
}
