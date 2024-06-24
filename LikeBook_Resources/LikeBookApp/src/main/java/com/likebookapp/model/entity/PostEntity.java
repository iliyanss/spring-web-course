package com.likebookapp.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> userLikes = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private MoodEntity mood;

    public PostEntity() {

    }

    public Long getId() {
        return id;
    }

    public PostEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public PostEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Set<UserEntity> getUserLikes() {
        return userLikes;
    }

    public PostEntity setUserLikes(Set<UserEntity> userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public MoodEntity getMood() {
        return mood;
    }

    public PostEntity setMood(MoodEntity mood) {
        this.mood = mood;
        return this;
    }
}
