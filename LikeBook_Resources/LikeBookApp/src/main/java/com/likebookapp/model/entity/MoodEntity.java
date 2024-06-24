package com.likebookapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class MoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "mood_name")
    @Enumerated(EnumType.STRING)
    private MoodEnum moodName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public MoodEntity() {

    }

    public Long getId() {
        return id;
    }

    public MoodEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public MoodEnum getMoodName() {
        return moodName;
    }

    public MoodEntity setMoodName(MoodEnum moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MoodEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
