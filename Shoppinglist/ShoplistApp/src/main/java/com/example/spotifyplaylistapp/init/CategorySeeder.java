package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.CategoryEntity;
import com.example.spotifyplaylistapp.model.entity.CategoryEnum;
import com.example.spotifyplaylistapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryEnum.values())
                .forEach(c -> {
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setCategoryName(c);
                    switch (c.getValue()) {
                        case "Food":
                            categoryEntity.setDescription("Food Category!");
                            break;
                        case "Drink":
                            categoryEntity.setDescription("Drink Category!");
                            break;
                        case "Household":
                            categoryEntity.setDescription("Household Category!");
                            break;
                        case "Other":
                            categoryEntity.setDescription("Other Category!");
                            break;
                    }
                    this.categoryRepository.save(categoryEntity);
                });
    }
}
