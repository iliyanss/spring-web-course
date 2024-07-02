package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;
import bg.softuni.mobilele.models.enums.TransmissionEnum;

public record OfferSummaryDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType, TransmissionEnum transmissionEnum) {

}