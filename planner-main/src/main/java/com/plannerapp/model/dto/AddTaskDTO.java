package com.plannerapp.model.dto;

import com.plannerapp.model.entity.PriorityEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddTaskDTO {
    private Long id;

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    @NotNull
    private String description;

    @NotNull(message = "You must select a priority!")
    private PriorityEnum priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Due Date must be in future!")
    private LocalDate dueDate;

    public AddTaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public AddTaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public AddTaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
