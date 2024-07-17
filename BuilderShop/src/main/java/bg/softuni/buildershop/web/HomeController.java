package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model, Principal principal){
        Long userId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity user = this.userService.findUserById(userId).orElse(null);
        List<ProductEntity> myProducts = user.getProducts();
        if (!myProducts.isEmpty()) {
            model.addAttribute("myProducts", myProducts);
        }
        return "home";
    }

}
