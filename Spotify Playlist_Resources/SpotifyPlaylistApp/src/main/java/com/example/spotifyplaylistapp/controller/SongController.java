package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddsongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {
    private final LoggedUser loggedUser;
    private final SongService songService;

    public SongController(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @ModelAttribute("addsongDTO")
    public AddsongDTO addsongDTO() {
        return new AddsongDTO();
    }

    @GetMapping("/song-add")
    public String addSong() {
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        return "song-add";
    }

    @PostMapping("/song-add")
    public String addSong(@Valid AddsongDTO addsongDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addsongDTO", addsongDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addsongDTO", bindingResult);

            return "redirect:/song-add";
        }

        this.songService.addSong(addsongDTO,loggedUser.getId());
        return "redirect:/home";
    }

}
