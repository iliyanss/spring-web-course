package com.bonappetit.model.dtos;

import com.bonappetit.model.entity.CategoryEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddRecipeDTO {
    private Long id;
    @Size(min = 2, max = 40, message = "The name length must be between 2 and 40 characters!")
    @NotNull
    private String name;
    @Size(min = 2, max = 150, message = "The ingredients length must be between 2 and 150 characters!")
    @NotNull
    private String ingredients;
    @NotNull(message = "You must select a category!")
    private CategoryEnum category;

    public AddRecipeDTO() {

    }

    public String getName() {
        return name;
    }

    public AddRecipeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public AddRecipeDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddRecipeDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddRecipeDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
