package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PaintingController {
    private final PaintingService paintingService;
    private final LoggedUser loggedUser;

    public PaintingController(PaintingService paintingService, LoggedUser loggedUser) {
        this.paintingService = paintingService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addPaintingDTO")
    public AddPaintingDTO addPaintingDTO() {
        return new AddPaintingDTO();
    }

    @GetMapping("/add-painting")
    public String addPainting() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "add-painting";
    }

    @PostMapping("/add-painting")
    public String addPainting(@Valid AddPaintingDTO addPaintingDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPaintingDTO", addPaintingDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPaintingDTO", bindingResult);

            return "redirect:/add-painting";
        }

        this.paintingService.addPainting(addPaintingDTO,loggedUser.getId());
        return "redirect:/home";
    }
}
