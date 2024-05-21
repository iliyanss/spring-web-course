package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.enums.EngineEnum;
import bg.softuni.mobilele.models.enums.TransmissionEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddOfferDto {
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;

    @Positive
    @NotNull
    private int price;

    @Min(1980)
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @NotNull
    @Min(1)
    private Long modelId;

    @NotEmpty
    private String imageUrl;

    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferDto setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public @NotEmpty String getImageUrl() {
        return imageUrl;
    }

    public AddOfferDto setImageUrl(@NotEmpty String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public @NotNull TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferDto setTransmission(@NotNull TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public @NotNull @Min(1) Long getModelId() {
        return modelId;
    }

    public AddOfferDto setModelId(@NotNull @Min(1) Long modelId) {
        this.modelId = modelId;
        return this;
    }
}
