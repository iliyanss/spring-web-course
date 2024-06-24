package com.likebookapp.controller;

import com.likebookapp.model.entity.PostEntity;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final PostService postService;

    public HomeController(LoggedUser loggedUser, UserService userService, PostService postService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        List<PostEntity> myPosts = this.postService.getAllMyPosts();
        model.addAttribute("myPosts", myPosts);
        List<PostEntity> otherPosts = this.postService.getOtherPosts();
        model.addAttribute("otherPosts", otherPosts);
        return "home";
    }
    @GetMapping("/remove-post/{id}")
    public String removePost(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = postService.removePostById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Post removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove post!");
        }
        return "redirect:/home";
    }
    @GetMapping("/like-post/{id}")
    public String likePost(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = postService.likePostById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Post liked successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to like post!");
        }
        return "redirect:/home";
    }
}
