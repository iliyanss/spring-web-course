package com.resellerapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity addedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity boughtBy;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    private ConditionEntity condition;

    public OfferEntity() {

    }

    public Long getId() {
        return id;
    }

    public OfferEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public OfferEntity setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ConditionEntity getCondition() {
        return condition;
    }

    public OfferEntity setCondition(ConditionEntity condition) {
        this.condition = condition;
        return this;
    }

    public UserEntity getBoughtBy() {
        return boughtBy;
    }

    public OfferEntity setBoughtBy(UserEntity boughtBy) {
        this.boughtBy = boughtBy;
        return this;
    }
}
