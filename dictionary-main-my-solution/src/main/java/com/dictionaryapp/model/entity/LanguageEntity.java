package com.dictionaryapp.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "language_name")
    @Enumerated(EnumType.STRING)
    private LanguageEnum languageName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<WordEntity> words = new HashSet<>();

    public LanguageEntity() {

    }

    public Long getId() {
        return id;
    }

    public LanguageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LanguageEnum getLanguageName() {
        return languageName;
    }

    public LanguageEntity setLanguageName(LanguageEnum languageName) {
        this.languageName = languageName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LanguageEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<WordEntity> getWords() {
        return words;
    }

    public LanguageEntity setWords(Set<WordEntity> words) {
        this.words = words;
        return this;
    }
}
