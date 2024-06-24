package com.paintingscollectors.service;

import com.paintingscollectors.model.entity.StyleEntity;
import com.paintingscollectors.model.entity.StyleEnum;
import com.paintingscollectors.repository.StyleRepository;
import org.springframework.stereotype.Service;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public StyleEntity findStyle(StyleEnum languageEnum) {
        return this.styleRepository.findByStyleName(languageEnum).orElseThrow();
    }
}
