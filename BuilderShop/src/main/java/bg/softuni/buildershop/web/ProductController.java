package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.AddProductDTO;
import bg.softuni.buildershop.model.dto.FavoriteProductDTO;
import bg.softuni.buildershop.model.dto.ProductSummaryDTO;
import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.service.CategoryService;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("addProductDTO")
    public AddProductDTO AddProductDTO() {
        return new AddProductDTO();
    }

    @ModelAttribute("allProducts")
    public List<ProductSummaryDTO> getAllProducts(Principal principal) {
        return this.productService.getAllProducts(principal);
    }
    @ModelAttribute("myFavorites")
    public List<FavoriteProductDTO> getFavoriteProducts(Principal principal) {
        return this.productService.getFavoriteProducts(principal);
    }

    @GetMapping("/add-product")
    public String addProduct() {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid AddProductDTO addProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal,
                             MultipartFile image
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addProductDTO", addProductDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/add-product";
        }
        Long userId = this.userService.getUserIdFromPrincipal(principal);


        this.productService.addProduct(addProductDTO, userId);
        return "redirect:/home";
    }


    @GetMapping("/all-products")
    public String allProducts(){
        return "all-products";
    }
    @GetMapping("/my-favorites")
    public String myFavoriteProducts(){
        return "my-favorites";
    }
    @GetMapping("/products/favorite/{id}")
    public String favoriteProduct(@PathVariable("id") Long id, Principal principal) {


        productService.addProductToFavorite(id,principal);

        return "redirect:/my-favorites";
    }
    @GetMapping("/products/remove-from-favorite/{id}")
    public String removeProductFromFavorite(@PathVariable("id") Long id, Principal principal) {
         this.productService.removeProductFromFavorites(id,principal);
         return "redirect:/my-favorites";
    }
}