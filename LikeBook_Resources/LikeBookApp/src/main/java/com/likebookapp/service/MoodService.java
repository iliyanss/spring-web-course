package com.likebookapp.service;

import com.likebookapp.model.entity.MoodEntity;
import com.likebookapp.model.entity.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import org.springframework.stereotype.Service;

@Service
public class MoodService {
    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public MoodEntity findMood(MoodEnum mood) {
        return this.moodRepository.findByMoodName(mood).orElseThrow();
    }
}
