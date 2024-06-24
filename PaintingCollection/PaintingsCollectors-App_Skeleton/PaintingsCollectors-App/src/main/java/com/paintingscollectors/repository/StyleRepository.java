package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.StyleEntity;
import com.paintingscollectors.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {
    Optional<StyleEntity> findByStyleName(StyleEnum languageEnum);
}
