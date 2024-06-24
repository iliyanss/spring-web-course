package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }
    public StyleEntity findStyle(StyleEnum styleEnum) {
        return this.styleRepository.findByStyleName(styleEnum).orElseThrow();
    }
}
