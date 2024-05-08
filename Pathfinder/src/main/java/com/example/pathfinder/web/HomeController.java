package com.example.pathfinder.web;

import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;

@Controller
public class HomeController {
    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model){
        Route route = this.routeService.getMostCommented();
        model.addAttribute("mostCommented",route);
        return "index";
    }

}
