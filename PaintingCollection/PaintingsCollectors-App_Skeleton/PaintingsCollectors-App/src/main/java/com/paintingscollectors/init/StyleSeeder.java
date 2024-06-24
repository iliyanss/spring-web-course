package com.paintingscollectors.init;

import com.paintingscollectors.model.entity.StyleEntity;
import com.paintingscollectors.model.entity.StyleEnum;
import com.paintingscollectors.repository.StyleRepository;
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
                        case "Impressionism":
                            styleEntity.setDescription("Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.");
                            break;
                        case "Abstract":
                            styleEntity.setDescription("Abstract art does not attempt to represent recognizable subjects in a realistic manner.");
                            break;
                        case "Expressionism":
                            styleEntity.setDescription("Expressionism is a style of art that doesn't concern itself with realism.");
                            break;
                        case "Surrealism":
                            styleEntity.setDescription("Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.");
                            break;
                        case "Realism":
                            styleEntity.setDescription("Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.");
                            break;
                    }
                    this.styleRepository.save(styleEntity);
                });
    }
}
