package bg.softuni.buildershop.model.dto;

import bg.softuni.buildershop.model.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class AddProductDTO {
    private Long id;
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    @NotNull
    private String name;
//    @Size(min = 5, max = 40, message = "Image url length must be between 40 and 200 characters!")
//    @NotNull
    private MultipartFile image;

    @NotNull(message = "You must select a category!")
    private CategoryEnum category;
    @NotNull
    @Size(min = 40, max = 500, message = "Description length must be between 40 and 500 characters!")
    private String description;
    @NotNull
    @Positive
    private double price;


    public AddProductDTO() {

    }

    public MultipartFile getImage() {
        return image;
    }

    public AddProductDTO setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!") @NotNull String getName() {
        return name;
    }

    public AddProductDTO setName(@Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!") @NotNull String name) {
        this.name = name;
        return this;
    }



    public @NotNull(message = "You must select a category!") CategoryEnum getCategory() {
        return category;
    }

    public AddProductDTO setCategory(@NotNull(message = "You must select a category!") CategoryEnum category) {
        this.category = category;
        return this;
    }

    public @NotNull @Size(min = 40, max = 500, message = "Description length must be between 40 and 500 characters!") String getDescription() {
        return description;
    }

    public AddProductDTO setDescription(@NotNull @Size(min = 40, max = 500, message = "Description length must be between 40 and 500 characters!") String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Positive
    public double getPrice() {
        return price;
    }

    public AddProductDTO setPrice(@NotNull @Positive double price) {
        this.price = price;
        return this;
    }
}
