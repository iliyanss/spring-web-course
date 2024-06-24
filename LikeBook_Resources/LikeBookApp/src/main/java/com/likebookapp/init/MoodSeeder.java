package com.likebookapp.init;

import com.likebookapp.model.entity.MoodEntity;
import com.likebookapp.model.entity.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MoodSeeder implements CommandLineRunner {

    private final MoodRepository moodRepository;

    public MoodSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.moodRepository.count() == 0) {
            Arrays.stream(MoodEnum.values())
                    .forEach(mood -> {
                        MoodEntity moodEntity = new MoodEntity();
                        moodEntity.setMoodName(mood);
                        switch (mood.getValue()) {
                            case "Happy":
                                moodEntity.setDescription("Happy Mood");
                                break;
                            case "Sad":
                                moodEntity.setDescription("Sad Mood");
                                break;
                            case "Inspired":
                                moodEntity.setDescription("Inspired Mood");
                                break;
                        }
                        this.moodRepository.save(moodEntity);
                    });
        }
    }
}
