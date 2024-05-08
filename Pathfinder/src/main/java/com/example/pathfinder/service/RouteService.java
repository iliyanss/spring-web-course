package com.example.pathfinder.service;

import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route getMostCommented(){
        return this.routeRepository.findMostCommented().orElse(null);
    }
}
