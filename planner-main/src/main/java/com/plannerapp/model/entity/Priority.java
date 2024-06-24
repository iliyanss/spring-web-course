package com.plannerapp.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PriorityEnum priorityName;
    @Column
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    public Priority() {
    }

    public PriorityEnum getPriorityName() {
        return priorityName;
    }

    public Priority setPriorityName(PriorityEnum moodName) {
        this.priorityName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }


    public Set<Task> getOffers() {
        return tasks;
    }

    public Priority setOffers(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
