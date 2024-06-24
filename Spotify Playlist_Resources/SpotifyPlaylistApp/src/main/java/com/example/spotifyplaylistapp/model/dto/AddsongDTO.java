package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddsongDTO {
    private Long id;
    @Size(min = 2, max = 20, message = "The title length must be between 2 and 20 characters!")
    @NotNull
    private String title;
    @Size(min = 3, max = 20, message = "The performer length must be between 3 and 20 characters!")
    @NotNull
    private String performer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The release date must be in the past or present!")
    private LocalDate releaseDate;
    @Positive
    private int duration;
    @NotNull(message = "You must select a style!")
    private StyleEnum style;

    public AddsongDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddsongDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddsongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public AddsongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddsongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AddsongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public AddsongDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
