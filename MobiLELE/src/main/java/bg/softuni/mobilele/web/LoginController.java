package bg.softuni.mobilele.web;

import bg.softuni.mobilele.models.dtos.UserLoginDTO;
import bg.softuni.mobilele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }


}
