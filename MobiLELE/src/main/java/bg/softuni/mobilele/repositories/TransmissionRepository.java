package bg.softuni.mobilele.repositories;

import bg.softuni.mobilele.models.entities.TransmissionEntity;
import bg.softuni.mobilele.models.enums.TransmissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<TransmissionEntity, Long> {
    TransmissionEntity findByName(TransmissionEnum transmissionEnum);
}
