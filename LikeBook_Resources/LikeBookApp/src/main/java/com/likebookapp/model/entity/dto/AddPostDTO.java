package com.likebookapp.model.entity.dto;

import com.likebookapp.model.entity.MoodEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostDTO {

    private Long id;
    @Size(min = 2, max = 50, message = "The content length must be between 2 and 60 characters!")
    @NotNull
    private String content;
    @NotNull(message = "You must select a language!")
    private MoodEnum mood;


    public AddPostDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddPostDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AddPostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public AddPostDTO setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }

}
