package com.bonappetit.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    private String ingredients;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserEntity addedBy;


    public RecipeEntity() {

    }

    public Long getId() {
        return id;
    }

    public RecipeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RecipeEntity setName(String name) {
        this.name = name;
        return this;
    }


    public CategoryEntity getCategory() {
        return category;
    }

    public RecipeEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public RecipeEntity setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
        return this;
    }


    public String getIngredients() {
        return ingredients;
    }

    public RecipeEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
