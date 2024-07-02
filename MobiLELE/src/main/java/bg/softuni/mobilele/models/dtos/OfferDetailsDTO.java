package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;
import bg.softuni.mobilele.models.enums.TransmissionEnum;

import java.util.List;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType,
                              int year,
                              TransmissionEnum transmission,
                              String imageUrl,
                              int price,
                              List<String> allCurrencies) {

}