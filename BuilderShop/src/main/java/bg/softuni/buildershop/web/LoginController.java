package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
