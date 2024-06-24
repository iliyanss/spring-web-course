package com.likebookapp.controller;

import com.likebookapp.model.entity.dto.AddPostDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;
    private final LoggedUser loggedUser;

    public PostController(PostService postService, LoggedUser loggedUser) {
        this.postService = postService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addpostDTO")
    public AddPostDTO addpostDTO() {
        return new AddPostDTO();
    }

    @GetMapping("/post-add")
    public String addPost() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "post-add";
    }

    @PostMapping("/post-add")
    public String addWord(@Valid AddPostDTO addpostDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addpostDTO", addpostDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addpostDTO", bindingResult);

            return "redirect:/post-add";
        }

        this.postService.addPost(addpostDTO,loggedUser.getId());
        return "redirect:/home";
    }
}
