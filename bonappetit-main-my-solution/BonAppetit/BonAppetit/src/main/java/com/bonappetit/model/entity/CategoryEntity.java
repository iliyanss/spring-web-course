package com.bonappetit.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "category_name")
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryName;

    @OneToMany(mappedBy = "category")
    private Set<RecipeEntity> recipes = new HashSet<>();

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public CategoryEntity() {

    }

    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryEnum getCategoryName() {
        return categoryName;
    }

    public CategoryEntity setCategoryName(CategoryEnum categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Set<RecipeEntity> getRecipes() {
        return recipes;
    }

    public CategoryEntity setRecipes(Set<RecipeEntity> recipes) {
        this.recipes = recipes;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
