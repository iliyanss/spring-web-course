package com.bonappetit.controller;

import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final RecipeService recipeService;

    public HomeController(LoggedUser loggedUser, UserService userService, RecipeService recipeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        List<RecipeEntity> allDesserts = this.recipeService.getAllDesserts();
        List<RecipeEntity> allMainDishes = this.recipeService.getAllMainDishes();
        List<RecipeEntity> allCocktails = this.recipeService.getAllCocktails();
        Set<RecipeEntity> allFavourites = this.recipeService.getAllFavouriteRecipes();
        model.addAttribute("allDesserts", allDesserts);
        model.addAttribute("allMainDishes", allMainDishes);
        model.addAttribute("allCocktails", allCocktails);
        model.addAttribute("allFavourites", allFavourites);
        return "home";
    }
    @GetMapping("/add-favourite/{id}")
    public String addFavouriteRecipe(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        this.recipeService.addRecipeById(id);
        return "redirect:/home";
    }
}
