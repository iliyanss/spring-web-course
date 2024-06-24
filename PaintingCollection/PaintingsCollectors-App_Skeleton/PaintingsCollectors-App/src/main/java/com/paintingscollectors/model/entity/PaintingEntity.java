package com.paintingscollectors.model.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paintings")
public class PaintingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private StyleEntity style;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserEntity owner;
    @Column(nullable = false)
    private String imageURL;
    @Column(nullable = false, name = "is_favorite")
    private boolean isFavorite;
    @Column(nullable = false)
    private int votes;

    public PaintingEntity() {
        isFavorite = false;
    }

    public Long getId() {
        return id;
    }

    public PaintingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingEntity setAuthor(String author) {
        this.author = author;
        return this;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public PaintingEntity setStyle(StyleEntity style) {
        this.style = style;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public PaintingEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PaintingEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public PaintingEntity setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public PaintingEntity setVotes(int votes) {
        this.votes = votes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintingEntity that = (PaintingEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
