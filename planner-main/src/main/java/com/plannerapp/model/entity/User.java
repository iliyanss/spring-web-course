package com.plannerapp.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> assignedTasks;


    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public User setAssignedTasks(Set<Task> tasks) {
        this.assignedTasks = tasks;
        return this;
    }


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
