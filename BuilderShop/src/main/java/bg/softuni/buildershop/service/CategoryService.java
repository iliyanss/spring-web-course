package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.enums.CategoryEnum;
import bg.softuni.buildershop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity findCategory(CategoryEnum categoryEnum) {
        return this.categoryRepository.findByName(categoryEnum).orElse(null);
    }
    public List<CategoryEntity> findAllCategories() {
        return this.categoryRepository.findAll();
    }
}
