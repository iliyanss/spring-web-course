package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.dtos.LoginDTO;
import com.dictionaryapp.model.entity.dtos.RegisterDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;
    private LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
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
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
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
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "/login";
    }
    @PostMapping("/login")
    public String loginConfirm(LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());

        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/login";
        }

        this.userService.login(loginDTO.getUsername());
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout(){
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        this.userService.logout();
        return "redirect:/";
    }
}
