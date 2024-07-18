package bg.softuni.buildershop.model.dto;

import bg.softuni.buildershop.model.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ProductSummaryDTO {

    Long id;
    String image;
    String name;
    double price;
    List<String> currencies;
    CategoryEnum category;
    String description;

    public ProductSummaryDTO() {
        currencies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ProductSummaryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductSummaryDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductSummaryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductSummaryDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public ProductSummaryDTO setCurrencies(List<String> currencies) {
        this.currencies = currencies;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductSummaryDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSummaryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
