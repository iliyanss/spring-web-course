package com.resellerapp.controller;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addOfferDTO")
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/offer-add")
    public String addOffer() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "offer-add";
    }

    @PostMapping("/offer-add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOfferDTO", addOfferDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/offer-add";
        }

        this.offerService.addOffer(addOfferDTO,loggedUser.getId());
        return "redirect:/home";
    }
}
