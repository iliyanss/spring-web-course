package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "category_name")
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryName;
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

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
