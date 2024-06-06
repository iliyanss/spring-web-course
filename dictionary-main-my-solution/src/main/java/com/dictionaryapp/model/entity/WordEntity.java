package com.dictionaryapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "words")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String term;
    @Column(nullable = false)
    private String translation;
    private String example;
    @Column(nullable = false, name = "input_date")
    private LocalDate inputDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private LanguageEntity language;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity addedBy;

    public WordEntity() {

    }

    public Long getId() {
        return id;
    }

    public WordEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordEntity setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordEntity setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordEntity setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public WordEntity setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public WordEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public WordEntity setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
