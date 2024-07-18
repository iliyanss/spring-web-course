package bg.softuni.buildershop.model.dto;

import bg.softuni.buildershop.model.enums.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

public class FavoriteProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private CategoryEnum category;

    public FavoriteProductDTO() {

    }

    public Long getId() {
        return id;
    }

    public FavoriteProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FavoriteProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FavoriteProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public FavoriteProductDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getImage() {
        return image;
    }

    public FavoriteProductDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public FavoriteProductDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
