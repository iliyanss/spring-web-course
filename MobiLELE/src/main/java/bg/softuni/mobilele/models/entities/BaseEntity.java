package bg.softuni.mobilele.models.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }
    @Column(nullable = false)
    public LocalDateTime getModified() {
        return modified;
    }

    public BaseEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public BaseEntity setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }
}