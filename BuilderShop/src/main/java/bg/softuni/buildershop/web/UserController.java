package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.LoginDTO;
import bg.softuni.buildershop.model.dto.RegisterDTO;
import bg.softuni.buildershop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
//@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }
    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String confirmRegister(@Valid RegisterDTO registerDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

        this.userService.register(registerDTO);
        return "redirect:/home";
    }
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
//    @PostMapping("/login")
//    public String loginConfirm(LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("loginDTO", loginDTO)
//                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
//
//            return "redirect:/login";
//        }
//
//        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());
//
//        if (!validCredentials) {
//            redirectAttributes
//                    .addFlashAttribute("loginDTO", loginDTO)
//                    .addFlashAttribute("validCredentials", false);
//            this.userService.login(loginDTO.getUsername());
//            return "redirect:/login";
//        }
//
//        return "redirect:/home";
//    }
}