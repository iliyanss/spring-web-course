package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.CategoryEntity;
import com.example.spotifyplaylistapp.model.entity.CategoryEnum;
import com.example.spotifyplaylistapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity findCategory(CategoryEnum categoryEnum) {
        return this.categoryRepository.findByCategoryName(categoryEnum).orElseThrow();
    }
}
