package bg.softuni.mobilele.models.entities;

import bg.softuni.mobilele.models.enums.TransmissionEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "transmissions")
public class TransmissionEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TransmissionEnum name;

    public TransmissionEntity() {

    }

    public TransmissionEnum getName() {
        return name;
    }

    public TransmissionEntity setName(TransmissionEnum name) {
        this.name = name;
        return this;
    }
}
