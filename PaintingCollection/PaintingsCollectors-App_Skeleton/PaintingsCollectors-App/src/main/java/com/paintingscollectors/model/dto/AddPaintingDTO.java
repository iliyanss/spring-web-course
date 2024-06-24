package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {
    private Long id;
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    @NotNull
    private String name;

    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters!")
    @NotNull
    private String author;
    @Size(max = 150, message = "Please enter valid image URL!")
    @NotNull
    private String image;
    @NotNull(message = "You must select a style!")
    private StyleEnum style;

    public AddPaintingDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddPaintingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddPaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImage() {
        return image;
    }

    public AddPaintingDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public AddPaintingDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
