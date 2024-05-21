package bg.softuni.mobilele.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class BrandDto {

    private List<ModelDto> models;

    private String name;

    public BrandDto() {
        models = new ArrayList<>();
    }

    public boolean addModel(ModelDto modelDto) {
        return models.add(modelDto);
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public BrandDto setModels(List<ModelDto> models) {
        this.models = models;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }
}
