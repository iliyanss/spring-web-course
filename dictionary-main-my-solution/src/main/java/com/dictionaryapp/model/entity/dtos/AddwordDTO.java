package com.dictionaryapp.model.entity.dtos;

import com.dictionaryapp.model.entity.LanguageEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddwordDTO {

    private Long id;
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    @NotNull
    private String term;
    @Size(min = 2, max = 60, message = "The translation length must be between 2 and 60 characters!")
    @NotNull
    private String translation;
    @Size(min = 2, max = 200, message = "The example length must be between 2 and 200 characters!")
    @NotNull
    private String example;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate inputDate;
    @NotNull(message = "You must select a language!")
    private LanguageEnum language;

    public AddwordDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddwordDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public AddwordDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public AddwordDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public AddwordDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public AddwordDTO setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public AddwordDTO setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }
}
