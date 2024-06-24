package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.ProductEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.ProductService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
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
    private final UserRepository userRepository;
    private final ProductService productService;

    public HomeController(LoggedUser loggedUser, UserService userService, UserRepository userRepository, ProductService productService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.userRepository = userRepository;
        this.productService = productService;
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
        List<ProductEntity>foods = this.productService.getAllFoods();
        model.addAttribute("foods", foods);
        List<ProductEntity> drinks = this.productService.getAllDrinks();
        model.addAttribute("drinks", drinks);
        List<ProductEntity> households = this.productService.getAllHouseholds();
        model.addAttribute("households", households);
        List<ProductEntity> others = this.productService.getAllOthers();
        model.addAttribute("others", others);
        double total = this.productService.getTotalPrice(foods,drinks,households,others);
        model.addAttribute("total", total);
        return "home";
    }
    @GetMapping("/buy-product/{id}")
    public String buyProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = productService.buyProduct(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Product removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove product!");
        }
        return "redirect:/home";
    }
    @GetMapping("/buy-all-products")
    public String buyAllProducts() {
        this.productService.buyAllProducts();

        return "redirect:/home";
    }
}
