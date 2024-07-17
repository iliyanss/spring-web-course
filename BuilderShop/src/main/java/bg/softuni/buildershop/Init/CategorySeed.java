package bg.softuni.buildershop.Init;

import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.enums.CategoryEnum;
import bg.softuni.buildershop.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CategorySeed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeed(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryEnum.values())
                .forEach(categoryEnum -> {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setName(categoryEnum);
                this.categoryRepository.save(categoryEntity);
                });
    }
}
