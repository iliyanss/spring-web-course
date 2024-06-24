package com.plannerapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Priority priority;

    @Column
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User assignedTo;


    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String content) {
        this.description = content;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public Task setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }



    public LocalDate getDueDate() {
        return dueDate;
    }

    public Task setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Task setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }
}
