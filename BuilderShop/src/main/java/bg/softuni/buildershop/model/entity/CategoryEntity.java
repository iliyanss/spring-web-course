package bg.softuni.buildershop.model.entity;

import bg.softuni.buildershop.model.enums.CategoryEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

    public CategoryEntity() {

    }

    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public CategoryEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }
}
