package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class LanguageSeeder implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public LanguageSeeder(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.languageRepository.count() != 0) {
            return;
        }
        Arrays.stream(LanguageEnum.values())
                .forEach(l -> {
                    LanguageEntity languageEntity = new LanguageEntity();
                    languageEntity.setLanguageName(l);
                    switch (l.getValue()) {
                        case "German":
                            languageEntity.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                            break;
                        case "Spanish":
                            languageEntity.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                            break;
                        case "French":
                            languageEntity.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                            break;
                        case "Italian":
                            languageEntity.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                            break;
                    }
                    this.languageRepository.save(languageEntity);
                });

    }
}
