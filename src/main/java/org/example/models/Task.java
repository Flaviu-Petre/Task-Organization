package org.example.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.*;

import java.util.*;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Task.class, name = "simple"),
        @JsonSubTypes.Type(value = PersonalTask.class, name = "personal"),
        @JsonSubTypes.Type(value = WorkTask.class, name = "work")
})
public class Task extends TaskManager{
    //region Variables
    private String _title;
    private String _description;
    private int _priority;
    private LocalDate _deadline;
    private boolean _isCompleted;
    private final String _type = "simple";
    //endregion

    //region Constructors
    public Task() {}
    public Task(String _title, String _description, int _priority, LocalDate _deadline, boolean _isCompleted) {
        this._title = _title;
        this._description = _description;
        this._priority = _priority;
        this._deadline = _deadline;
        this._isCompleted = _isCompleted;
    }
    //endregion

    //region Getters
    public String get_type() {
        return _type;
    }

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _description;
    }

    public int get_priority() {
        return _priority;
    }

    public LocalDate get_deadline() {
        return _deadline;
    }

    public boolean get_isCompleted() {
        return _isCompleted;
    }
    //endregion

    //region Setters

    public void set_title(String _title) {this._title = _title;}

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_priority(int _priority) {
        this._priority = _priority;
    }

    public void set_deadline(LocalDate _deadline) {
        this._deadline = _deadline;
    }

    public void set_isCompleted(boolean _isCompleted) { this._isCompleted = _isCompleted; }
    //endregion
}
