package com.resellerapp.model.entity;

import javax.persistence.*;
import java.util.HashSet;
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
    @OneToMany(mappedBy = "addedBy", fetch = FetchType.EAGER)
    Set<OfferEntity> offers = new HashSet<OfferEntity>();
    @OneToMany(mappedBy = "boughtBy", fetch = FetchType.EAGER)
    Set<OfferEntity> boughtOffers = new HashSet<OfferEntity>();

    public UserEntity() {

    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
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

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public Set<OfferEntity> getBoughtOffers() {
        return boughtOffers;
    }

    public UserEntity setBoughtOffers(Set<OfferEntity> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
