package com.bonappetit.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RecipeEntity> addedRecipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RecipeEntity> favouriteRecipes = new HashSet<>();

    public UserEntity() {

    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RecipeEntity> getAddedRecipes() {
        return addedRecipes;
    }

    public UserEntity setAddedRecipes(Set<RecipeEntity> addedRecipes) {
        this.addedRecipes = addedRecipes;
        return this;
    }

    public Set<RecipeEntity> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public UserEntity setFavouriteRecipes(Set<RecipeEntity> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
        return this;
    }
}