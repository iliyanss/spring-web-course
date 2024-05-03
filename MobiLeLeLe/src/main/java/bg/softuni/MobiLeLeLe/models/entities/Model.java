package bg.softuni.MobiLeLeLe.models.entities;

import bg.softuni.MobiLeLeLe.models.enums.Category;
import jakarta.persistence.*;

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

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(name = "start_year")
    public int getStartYear() {
        return startYear;
    }

    @Column(name = "end_year")
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
