package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<PaintingEntity> paintings = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PaintingEntity> favoritePaintings = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PaintingEntity> ratedPaintings = new HashSet<>();

    public UserEntity() {

    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<PaintingEntity> getPaintings() {
        return paintings;
    }

    public UserEntity setPaintings(Set<PaintingEntity> paintings) {
        this.paintings = paintings;
        return this;
    }

    public Set<PaintingEntity> getFavoritePaintings() {
        return favoritePaintings;
    }

    public UserEntity setFavoritePaintings(Set<PaintingEntity> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
        return this;
    }

    public Set<PaintingEntity> getRatedPaintings() {
        return ratedPaintings;
    }

    public UserEntity setRatedPaintings(Set<PaintingEntity> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
