package com.example.linkedout.model.dtos;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CompanyDto {
    @NotBlank
    @Size(min = 2, max = 10)
    private String name;
    @NotBlank
    @Size(min = 2, max = 10)
    private String town;
    @NotBlank
    @Size(min = 10)
    private String description;
    @Positive
    private BigDecimal budget;

    public CompanyDto() {

    }

    public String getName() {
        return name;
    }

    public CompanyDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getTown() {
        return town;
    }

    public CompanyDto setTown(String town) {
        this.town = town;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CompanyDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public CompanyDto setBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }
}
