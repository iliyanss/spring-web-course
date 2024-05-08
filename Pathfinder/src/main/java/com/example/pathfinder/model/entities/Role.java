package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.UserRoles;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoles name;

    public Role() {

    }

    public UserRoles getName() {
        return name;
    }

    public Role setName(UserRoles name) {
        this.name = name;
        return this;
    }
}
