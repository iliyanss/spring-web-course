package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class StyleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "style_name")
    @Enumerated(EnumType.STRING)
    private StyleEnum styleName;
    @Column(columnDefinition = "TEXT")
    private String description;
    public StyleEntity() {

    }

    public Long getId() {
        return id;
    }

    public StyleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public StyleEntity setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StyleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
