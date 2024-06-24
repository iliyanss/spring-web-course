package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.CategoryEntity;
import com.example.spotifyplaylistapp.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategoryEquals(CategoryEntity categoryEntity);
}
