package com.resellerapp.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conditions")
public class ConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "condition_name")
    @Enumerated(EnumType.STRING)
    private ConditionEnum conditionName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "condition")
    private Set<OfferEntity> offers = new HashSet<>();

    public ConditionEntity() {

    }

    public Long getId() {
        return id;
    }

    public ConditionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ConditionEnum getConditionName() {
        return conditionName;
    }

    public ConditionEntity setConditionName(ConditionEnum conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ConditionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public ConditionEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }
}
