package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.enums.CategoryEnum;
import bg.softuni.buildershop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(CategoryEnum.BOOKS);
        when(categoryRepository.findByName(CategoryEnum.BOOKS)).thenReturn(Optional.of(categoryEntity));

        CategoryEntity result = categoryService.findCategory(CategoryEnum.BOOKS);

        assertEquals(categoryEntity, result);
    }

    @Test
    void testFindCategory_NotFound() {
        when(categoryRepository.findByName(CategoryEnum.BOOKS)).thenReturn(Optional.empty());

        CategoryEntity result = categoryService.findCategory(CategoryEnum.BOOKS);

        assertNull(result);
    }

    @Test
    void testFindAllCategories() {
        CategoryEntity categoryEntity1 = new CategoryEntity();
        CategoryEntity categoryEntity2 = new CategoryEntity();
        List<CategoryEntity> categories = List.of(categoryEntity1, categoryEntity2);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryEntity> result = categoryService.findAllCategories();

        assertEquals(2, result.size());
        assertEquals(categories, result);
    }
}
