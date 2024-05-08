package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.RouteCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private RouteCategoryEnum name;

    public Category() {

    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public RouteCategoryEnum getName() {
        return name;
    }

    public Category setName(RouteCategoryEnum name) {
        this.name = name;
        return this;
    }
}
