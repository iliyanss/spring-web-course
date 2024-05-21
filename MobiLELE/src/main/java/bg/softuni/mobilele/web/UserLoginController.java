package bg.softuni.mobilele.web;

import bg.softuni.mobilele.models.dtos.UserLoginDTO;
import bg.softuni.mobilele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){

        return "auth-login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.userService.login(userLoginDTO)) {
        redirectAttributes.addFlashAttribute("userModel",userLoginDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",bindingResult);
        bindingResult.rejectValue("password", "InvalidPasswordError","Invalid Password.");
            return "redirect:/users/login";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

}
