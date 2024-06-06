package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.dtos.AddwordDTO;
import com.dictionaryapp.model.entity.dtos.RegisterDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class WordController {

    private final WordService wordService;
    private final LoggedUser loggedUser;

    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addwordDTO")
    public AddwordDTO addwordDTO() {
        return new AddwordDTO();
    }

    @GetMapping("/word-add")
    public String addWord() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "word-add";
    }

    @PostMapping("/tasks/word-add")
    public String addWord(@Valid AddwordDTO addwordDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
                          ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addwordDTO", addwordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addwordDTO", bindingResult);

            return "redirect:/word-add";
        }

        this.wordService.addWord(addwordDTO,loggedUser.getId());
        return "redirect:/home";
    }

}
