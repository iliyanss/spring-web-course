package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {
    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Long userId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity user = this.userService.findUserById(userId).orElse(null);
        List<ProductEntity> myProducts = user.getProducts();
        if (!myProducts.isEmpty()) {
            model.addAttribute("myProducts", myProducts);
        }
        return "home";
    }

    @GetMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id,
                                Principal principal) {

        this.productService.removeProductById(id, principal);

        return "redirect:/home";

    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
