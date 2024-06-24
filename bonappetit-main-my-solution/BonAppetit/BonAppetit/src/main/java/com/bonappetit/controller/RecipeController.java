package com.bonappetit.controller;

import com.bonappetit.model.dtos.AddRecipeDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RecipeController {

    private final LoggedUser loggedUser;
    private final RecipeService recipeService;
    public RecipeController(LoggedUser loggedUser, RecipeService recipeService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
    }
    @ModelAttribute("addRecipeDTO")
    public AddRecipeDTO addRecipeDTO() {
        return new AddRecipeDTO();
    }

    @GetMapping("/recipe-add")
    public String addRecipe() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "recipe-add";
    }

    @PostMapping("/recipe-add")
    public String addWord(@Valid AddRecipeDTO addRecipeDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addRecipeDTO", addRecipeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addRecipeDTO", bindingResult);

            return "redirect:/recipe-add";
        }

        this.recipeService.addRecipe(addRecipeDTO,loggedUser.getId());
        return "redirect:/home";
    }
}
