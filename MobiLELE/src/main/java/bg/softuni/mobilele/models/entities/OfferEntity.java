package bg.softuni.mobilele.models.entities;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;

import bg.softuni.mobilele.models.enums.TransmissionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Positive
    private Integer mileage;
    @Column(nullable = false)
    @Positive
    private int price;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private TransmissionEntity transmission;

    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;

    public Integer getMileage() {
        return mileage;
    }

    public OfferEntity setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public OfferEntity setEngine(EngineTypeEnum engine) {
        this.engine = engine;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TransmissionEntity getTransmission() {
        return transmission;
    }

    public OfferEntity setTransmission(TransmissionEntity transmission) {
        this.transmission = transmission;
        return this;
    }
}
