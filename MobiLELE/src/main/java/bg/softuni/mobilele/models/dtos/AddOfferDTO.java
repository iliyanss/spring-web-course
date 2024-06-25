package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;

import javax.validation.constraints.*;

public record AddOfferDTO(
        @NotEmpty(message = "{add.offer.description.not.empty}")
        @Size(message = "Description should be between 5 and 500 symbols.",
                min = 5,
                max = 500) String description,//not necessarily from message source
        @NotNull @PositiveOrZero Integer mileage,
        @NotNull EngineTypeEnum engineType
) {

    public static AddOfferDTO empty() {
        return new AddOfferDTO(null, null, null);
    }

}
