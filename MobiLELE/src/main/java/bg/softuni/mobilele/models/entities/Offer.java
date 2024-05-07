package bg.softuni.mobilele.models.entities;

import bg.softuni.mobilele.models.enums.EngineEnum;
import bg.softuni.mobilele.models.enums.TransmissionEnum;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class Offer {
    private UUID id;
    private String description;
    private EngineEnum engine;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private TransmissionEnum transmission;
    private int year;

    private Model model;
    private User user;

    public Offer() {

    }
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    public UUID getId() {
        return id;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EngineEnum getEngine() {
        return engine;
    }
    @Column(nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }
    @Column(nullable = false)
    public LocalDateTime getModified() {
        return modified;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }
    @Column(nullable = false)
    public int getMileage() {
        return mileage;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TransmissionEnum getTransmission() {
        return transmission;
    }
    @Column(nullable = false)
    public int getYear() {
        return year;
    }
    @ManyToOne
    public Model getModel() {
        return model;
    }
    @ManyToOne
    public User getUser() {
        return user;
    }

    public Offer setId(UUID id) {
        this.id = id;
        return this;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Offer setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Offer setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public Offer setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public Offer setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Offer setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Offer setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Offer setYear(int year) {
        this.year = year;
        return this;
    }

    public Offer setModel(Model model) {
        this.model = model;
        return this;
    }

    public Offer setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", created=" + created +
                ", modified=" + modified +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", model=" + model +
                ", user=" + user +
                '}';
    }
}
