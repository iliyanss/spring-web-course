package bg.softuni.mobilele.models.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{

    private String name;
    private List<Model> models = new ArrayList<>();


    public Brand() {

    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Model> getModels() {
        return models;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}
