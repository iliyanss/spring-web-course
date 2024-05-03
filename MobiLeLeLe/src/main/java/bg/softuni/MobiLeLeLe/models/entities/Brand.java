package bg.softuni.MobiLeLeLe.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{

    private String name;
    private Set<Model> models = new HashSet<>();


    public Brand() {

    }

    @OneToMany(mappedBy = "brand")
    public Set<Model> getModels() {
        return models;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public void setName(String name) {
        this.name = name;
    }
}
