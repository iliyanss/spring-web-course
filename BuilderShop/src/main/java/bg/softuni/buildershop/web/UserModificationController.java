package bg.softuni.buildershop.web;

import bg.softuni.buildershop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UserModificationController {
    private final UserService userService;

    public UserModificationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsersExceptAdmin());
        return "user-list";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/change-username/{id}")
    public String changeUsernameForm(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        return "change-username";
    }

    @PostMapping("/users/change-username")
    public String changeUsername(@RequestParam Long id, @RequestParam String newUsername) {
        userService.updateUsername(id, newUsername);
        return "redirect:/admin/users";
    }
}
