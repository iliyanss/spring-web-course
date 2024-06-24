package com.bonappetit.service;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity findCategory(CategoryEnum category) {
        return this.categoryRepository.findByCategoryName(category).orElseThrow();
    }
}
