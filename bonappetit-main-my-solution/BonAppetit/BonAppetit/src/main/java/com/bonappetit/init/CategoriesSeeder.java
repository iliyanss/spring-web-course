package com.bonappetit.init;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoriesSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoriesSeeder(CategoryRepository categoryRepository) {
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
                        case "MainDish":
                            categoryEntity.setDescription("Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                            break;
                        case "Dessert":
                            categoryEntity.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                            break;
                        case "Cocktail":
                            categoryEntity.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                            break;
                    }
                    this.categoryRepository.save(categoryEntity);
                });
    }
}


