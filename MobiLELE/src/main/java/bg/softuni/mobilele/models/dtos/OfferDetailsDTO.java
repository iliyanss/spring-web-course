package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType) {

}