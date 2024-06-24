package com.resellerapp.controller;

import com.resellerapp.model.entity.OfferEntity;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"boughtItems", "allOtherOffers"})
public class HomeController {
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final OfferService offerService;

    public HomeController(UserService userService, LoggedUser loggedUser, OfferService offerService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.offerService = offerService;
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

        List<OfferEntity> myOffers = this.offerService.getMyOffers(loggedUser.getId());
        model.addAttribute("myOffers", myOffers);

        if (!model.containsAttribute("allOtherOffers")) {
            List<OfferEntity> allOtherOffers = this.offerService.getOtherOffers(loggedUser.getId());
            model.addAttribute("allOtherOffers", allOtherOffers);
        }

        if (!model.containsAttribute("boughtItems")) {
            model.addAttribute("boughtItems", new ArrayList<>());
        }

        return "home";
    }

    @GetMapping("/remove-offer/{id}")
    public String removeOffer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = offerService.removeOfferById(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Offer removed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to remove offer!");
        }
        return "redirect:/home";
    }

    @GetMapping("/buy-offer/{id}")
    public String buyOffer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {
        OfferEntity offerToBuy = this.offerService.getOfferById(id);
        if (offerToBuy != null) {
            List<OfferEntity> boughtItems = (List<OfferEntity>) model.getAttribute("boughtItems");
            boughtItems.add(offerToBuy);

            List<OfferEntity> allOtherOffers = (List<OfferEntity>) model.getAttribute("allOtherOffers");
            allOtherOffers.removeIf(offer -> offer.getId().equals(id));

            redirectAttributes.addFlashAttribute("message", "Offer bought successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to buy offer!");
        }
        return "redirect:/home";
    }

    @GetMapping("/clear-session")
    public String clearSession(SessionStatus status) {
        status.setComplete();
        return "redirect:/home";
    }
}
