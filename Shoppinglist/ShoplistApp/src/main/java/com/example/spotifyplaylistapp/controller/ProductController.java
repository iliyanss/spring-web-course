package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddProductDTO;
import com.example.spotifyplaylistapp.service.ProductService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final LoggedUser loggedUser;

    public ProductController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addproductDTO")
    public AddProductDTO addproductDTO() {
        return new AddProductDTO();
    }

    @GetMapping("/product-add")
    public String addWord() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "product-add";
    }

    @PostMapping("/product-add")
    public String addWord(@Valid AddProductDTO addproductDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addproductDTO", addproductDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addproductDTO", bindingResult);

            return "redirect:/product-add";
        }

        this.productService.addProduct(addproductDTO,loggedUser.getId());
        return "redirect:/home";
    }
}
