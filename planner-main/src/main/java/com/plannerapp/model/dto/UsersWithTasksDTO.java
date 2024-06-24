package com.plannerapp.model.dto;

import java.util.HashSet;
import java.util.Set;

public class UsersWithTasksDTO {
    private String username;
    private Long id;
    private Set<TaskDTO> tasks;

    public UsersWithTasksDTO() {
        this.tasks = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public UsersWithTasksDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UsersWithTasksDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<TaskDTO> getOffers() {
        return tasks;
    }

    public UsersWithTasksDTO setOffers(Set<TaskDTO> tasks) {
        this.tasks = tasks;
        return this;
    }

//    public Set<User> getUserLikes() {
//        return userLikes;
//    }
//
//    public OffersWithUsernamesDTO setUserLikes(Set<User> userLikes) {
//        this.userLikes = userLikes;
//        return this;
//    }

//    public boolean checkIfUserIdMatchId(Long userId){
//        return this.getUserLikes().stream()
//                .anyMatch(user -> {
//                    boolean r = user.getId().equals(userId);
//                    return r;
//                });
//    }
}
