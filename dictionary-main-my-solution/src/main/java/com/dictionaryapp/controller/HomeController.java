package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final WordService wordService;

    public HomeController(LoggedUser loggedUser, UserService userService, WordService wordService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.wordService = wordService;
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
        List<WordEntity> allGermanWords = this.wordService.getAllGermanWords();
        List<WordEntity> allFrenchWords = this.wordService.getAllFrenchWords();
        List<WordEntity> allSpanishWords = this.wordService.getAllSpanishWords();
        List<WordEntity> allItalianWords = this.wordService.getAllItalianWords();
        int allWordsCount =
                allGermanWords.size() +
                        allFrenchWords.size() +
                        allSpanishWords.size() +
                        allItalianWords.size();
        model.addAttribute("allGermanWords", allGermanWords);
        model.addAttribute("allFrenchWords", allFrenchWords);
        model.addAttribute("allSpanishWords", allSpanishWords);
        model.addAttribute("allItalianWords", allItalianWords);
        model.addAttribute("allWordsCount", allWordsCount);

        return "home";
    }
    @GetMapping("/remove-word/{id}")
    public String removeWord(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = wordService.removeWordById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Word removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove word!");
        }
        return "redirect:/home";
    }
    @GetMapping("/remove-all-words")
    public String removeAllWords(RedirectAttributes redirectAttributes) {
        boolean success = wordService.removeAllWords();
        if (success) {
            redirectAttributes.addFlashAttribute("message", "All words removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove all words!");
        }
        return "redirect:/home";
    }


}
