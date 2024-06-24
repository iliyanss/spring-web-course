package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
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
    private final SongService songService;
    private final UserRepository userRepository;

    public HomeController(LoggedUser loggedUser, UserService userService, SongService songService, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.songService = songService;
        this.userRepository = userRepository;
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
        UserEntity userEntity = this.userRepository
                .findByUsername(loggedUser.getUsername()).orElse(null);
        Set<SongEntity> playlist = userEntity.getPlaylist();
        model.addAttribute("playlist", playlist);
        List<SongEntity> popSongs = this.songService.getAllPopSongs();
        model.addAttribute("popSongs", popSongs);
        List<SongEntity> rockSongs = this.songService.getAllRockSongs();
        model.addAttribute("rockSongs", rockSongs);
        List<SongEntity> jazzSongs = this.songService.getAllJazzSongs();
        model.addAttribute("jazzSongs", jazzSongs);
        int totalMins = 0;
        for (SongEntity songEntity : playlist) {
            totalMins+=songEntity.getDuration();
        }
        model.addAttribute("totalMins", totalMins);



        return "home";
    }
    @GetMapping("/add-song-favourite/{id}")
    public String addSong(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean success = songService.addSongToPlaylist(id);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Song added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to add song!");
        }
        return "redirect:/home";
    }
    @GetMapping("/remove-all")
    public String removeAll() {
        songService.removeAllSongs();
        return "redirect:/home";
    }
}
