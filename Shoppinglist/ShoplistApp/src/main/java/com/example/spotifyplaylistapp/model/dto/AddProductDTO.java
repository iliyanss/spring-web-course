package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.CategoryEnum;
import jdk.jfr.Category;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddProductDTO {
    private Long id;
    @Size(min = 3, max = 20, message = "The name length must be between 2 and 40 characters!")
    @NotNull
    private String name;
    @Size(min = 5, message = "The description length must be more than 5 characters!")
    @NotNull
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDateTime neededBefore;
    @Positive(message = "Price must be a positive number!")
    @NotNull
    private Double price;
    @NotNull(message = "You must select a category!")
    private CategoryEnum category;
    public AddProductDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public @Size(min = 3, max = 20, message = "The name length must be between 2 and 40 characters!") @NotNull String getName() {
        return name;
    }

    public AddProductDTO setName(@Size(min = 3, max = 20, message = "The name length must be between 2 and 40 characters!") @NotNull String name) {
        this.name = name;
        return this;
    }

    public @Size(min = 5, message = "The description length must be more than 5 characters!") @NotNull String getDescription() {
        return description;
    }

    public AddProductDTO setDescription(@Size(min = 5, message = "The description length must be more than 5 characters!") @NotNull String description) {
        this.description = description;
        return this;
    }

    public @PastOrPresent(message = "The input date must be in the past or present!") LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public AddProductDTO setNeededBefore(@PastOrPresent(message = "The input date must be in the past or present!") LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public @Positive(message = "Price must be a positive number!") @NotNull Double getPrice() {
        return price;
    }

    public AddProductDTO setPrice(@Positive(message = "Price must be a positive number!") @NotNull Double price) {
        this.price = price;
        return this;
    }

    public @NotNull(message = "You must select a category!") CategoryEnum getCategory() {
        return category;
    }

    public AddProductDTO setCategory(@NotNull(message = "You must select a category!") CategoryEnum category) {
        this.category = category;
        return this;
    }
}
