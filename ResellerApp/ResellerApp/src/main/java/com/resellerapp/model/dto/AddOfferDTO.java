package com.resellerapp.model.dto;

import com.resellerapp.model.entity.ConditionEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddOfferDTO {

    private Long id;
    @NotNull(message = "You must select a condition!")
    private ConditionEnum condition;

    @Size(min = 2, max = 50, message = "The description length must be between 2 and 50 characters!")
    @NotNull
    private String description;

    @NotNull
    @Positive(message = "Price must be a positive number!")
    private Double price;

    public AddOfferDTO() {

    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public AddOfferDTO setCondition(ConditionEnum condition) {
        this.condition = condition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddOfferDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
