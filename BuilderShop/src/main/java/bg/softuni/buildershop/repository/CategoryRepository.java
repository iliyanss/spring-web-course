package bg.softuni.buildershop.repository;

import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum categoryEnum);
}
