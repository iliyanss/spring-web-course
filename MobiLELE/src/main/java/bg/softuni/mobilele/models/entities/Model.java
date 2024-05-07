package bg.softuni.mobilele.models.entities;

import bg.softuni.mobilele.models.enums.Category;
import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model extends BaseEntity{

    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private Brand brand;

    public Model() {

    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Category getCategory() {
        return category;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(name = "start_year", nullable = false)
    public int getStartYear() {
        return startYear;
    }

    @Column(name = "end_year", nullable = true)
    public int getEndYear() {
        return endYear;
    }

    @ManyToOne
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public Model setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Model setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public Model setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand=" + brand +
                '}';
    }
}
