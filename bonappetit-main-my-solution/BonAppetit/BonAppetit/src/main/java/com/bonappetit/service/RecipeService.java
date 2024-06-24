package com.bonappetit.service;

import com.bonappetit.model.dtos.AddRecipeDTO;
import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public RecipeService(RecipeRepository recipeRepository, CategoryService categoryService, UserService userService, UserRepository userRepository, LoggedUser loggedUser) {
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public void addRecipe(AddRecipeDTO addRecipeDTO, Long id) {
        RecipeEntity recipeEntity = new RecipeEntity();
        CategoryEntity categoryEntity = this.categoryService.findCategory(addRecipeDTO.getCategory());
        UserEntity userEntity = this.userService.findUserById(id).orElse(null);
        recipeEntity.setCategory(categoryEntity);
        recipeEntity.setName(addRecipeDTO.getName());
        recipeEntity.setAddedBy(userEntity);
        userEntity.getAddedRecipes().add(recipeEntity);
        recipeEntity.setIngredients(addRecipeDTO.getIngredients());
        recipeRepository.save(recipeEntity);
        userRepository.save(userEntity);
    }

    public List<RecipeEntity> getAllDesserts() {
        CategoryEntity category = this.categoryService.findCategory(CategoryEnum.DESSERT);
        return this.recipeRepository.findAllByCategoryEquals(category);
    }

    public List<RecipeEntity> getAllMainDishes() {
        CategoryEntity category = this.categoryService.findCategory(CategoryEnum.MAIN_DISH);
        return this.recipeRepository.findAllByCategoryEquals(category);
    }

    public List<RecipeEntity> getAllCocktails() {
        CategoryEntity category = this.categoryService.findCategory(CategoryEnum.COCKTAIL);
        return this.recipeRepository.findAllByCategoryEquals(category);
    }

    public void addRecipeById(Long id) {
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        RecipeEntity recipeEntity = this.recipeRepository.findById(id).orElse(null);
        if (userEntity.getFavouriteRecipes().contains(recipeEntity)){
            return;
        }
        userEntity.getFavouriteRecipes().add(recipeEntity);
        this.userRepository.save(userEntity);
    }

    public Set<RecipeEntity> getAllFavouriteRecipes() {
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        return userEntity.getFavouriteRecipes();
    }
}
