package com.paintingscollectors.controller;

import com.paintingscollectors.model.entity.PaintingEntity;
import com.paintingscollectors.model.entity.UserEntity;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private final PaintingService paintingService;
    private final UserService userService;

    private final LoggedUser loggedUser;

    public HomeController(PaintingService paintingService, UserService userService, LoggedUser loggedUser) {
        this.paintingService = paintingService;
        this.userService = userService;
        this.loggedUser = loggedUser;
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
        UserEntity currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);

        Set<PaintingEntity> myPaintings = this.paintingService.getMyPaintings();
        model.addAttribute("myPaintings", myPaintings);
        Set<PaintingEntity> myFavorites = currentUser.getFavoritePaintings();
        model.addAttribute("myFavorites", myFavorites);
        Set<PaintingEntity> otherPaintings = this.paintingService.getOtherPaintings();
        model.addAttribute("otherPaintings", otherPaintings);
        List<PaintingEntity> mostVotedPaintings = this.paintingService.getMostVotedPaintings();
        model.addAttribute("mostVotedPaintings", mostVotedPaintings);

        return "home";
    }
    @GetMapping("/paintings/remove/{id}")
    public String removePainting(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        boolean success = paintingService.removePaintingById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Painting removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove painting!");
        }
        return "redirect:/home";
    }
    @GetMapping("/paintings/favorite/{id}")
    public String favoritePainting(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        boolean success = paintingService.addPaintingToFavorites(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Painting added to favorites successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to add painting to favorites!");
        }

        return "redirect:/home";
    }
    @GetMapping("/paintings/vote/{id}")
    public String votePainting(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        boolean success = paintingService.votePaintingById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Painting voted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to vote painting!");
        }
        return "redirect:/home";
    }
    @GetMapping("/paintings/remove-favorite/{id}")
    public String removePaintingFromFavorites(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        this.paintingService.removeFromFavorites(id);
        return "redirect:/home";
    }
}
