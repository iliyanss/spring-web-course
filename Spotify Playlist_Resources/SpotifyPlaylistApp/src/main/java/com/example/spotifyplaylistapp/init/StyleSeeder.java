package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StyleSeeder implements CommandLineRunner {
    private final StyleRepository styleRepository;

    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.styleRepository.count() != 0) {
            return;
        }
        Arrays.stream(StyleEnum.values())
                .forEach(s -> {
                    StyleEntity styleEntity = new StyleEntity();
                    styleEntity.setStyleName(s);
                    switch (s.getValue()) {
                        case "Pop":
                            styleEntity.setDescription("Pop Style!");
                            break;
                        case "Rock":
                            styleEntity.setDescription("Rock Style!");
                            break;
                        case "Jazz":
                            styleEntity.setDescription("Jazz Style!");
                            break;
                    }
                    this.styleRepository.save(styleEntity);
                });
    }
}
