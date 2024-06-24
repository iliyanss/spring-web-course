package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.CategoryEntity;
import com.example.spotifyplaylistapp.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCategoryName(CategoryEnum categoryEnum);
}
