package com.bonappetit.repo;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findAllByCategoryEquals(CategoryEntity category);
}
