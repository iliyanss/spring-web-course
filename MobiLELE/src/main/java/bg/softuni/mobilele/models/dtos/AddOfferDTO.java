package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;

import bg.softuni.mobilele.models.enums.TransmissionEnum;
import jakarta.validation.constraints.*;

public record AddOfferDTO(
        @NotEmpty(message = "{add.offer.description.not.empty}")
        @Size(message = "Description should be between 5 and 500 symbols.",
                min = 5,
                max = 500) String description,//not necessarily from message source
        @NotNull @Positive Integer mileage,
        @Positive int price,
        @NotNull EngineTypeEnum engineType,
        @Positive int year,
        @NotNull String imageUrl,
        @NotNull TransmissionEnum transmission
) {

    public static AddOfferDTO empty() {
        return new AddOfferDTO(null,
                null,
                0,
                null,
                0,
                null,
                null);
    }

}
