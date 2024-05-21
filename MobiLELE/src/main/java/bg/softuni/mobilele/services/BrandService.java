package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDto> getAllBrands() {
        return this.brandRepository
                .findAll()
                .stream()
                .map(this::mapToBrand).collect(Collectors.toList());
    }

    private BrandDto mapToBrand(Brand brand){

       List<ModelDto> models = brand
                .getModels()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());

        return new BrandDto()
                .setModels(models)
                .setName(brand.getName());
    }

    private ModelDto mapToModel(Model model){
        return new ModelDto()
                .setId(model.getId())
                .setName(model.getName());

    }
}
