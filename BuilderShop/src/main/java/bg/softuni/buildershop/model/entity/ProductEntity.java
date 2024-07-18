package bg.softuni.buildershop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Positive
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @ManyToMany(mappedBy = "favoriteProducts", fetch = FetchType.EAGER)
    private List<UserEntity> usersWhoFavorited = new ArrayList<>();



    public ProductEntity() {

    }

    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Positive
    public double getPrice() {
        return price;
    }

    public ProductEntity setPrice(@Positive double price) {
        this.price = price;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductEntity setImage(String image) {
        this.image = image;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ProductEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public List<UserEntity> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public ProductEntity setUsersWhoFavorited(List<UserEntity> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
        return this;
    }
}
