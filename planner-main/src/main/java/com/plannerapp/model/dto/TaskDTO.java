package com.plannerapp.model.dto;

import com.plannerapp.model.entity.PriorityEnum;

import java.time.LocalDate;

public class TaskDTO {
    private Long id;

    private String description;

    private PriorityEnum priority;

    private LocalDate dueDate;

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public TaskDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public TaskDTO setPriority(PriorityEnum priority) {
        this.priority = priority;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
