package bg.softuni.mobilele.config;

import bg.softuni.mobilele.models.dtos.AddOfferDto;
import bg.softuni.mobilele.models.dtos.UserRegisterDTO;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.entities.User;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mapping(target = "active", constant = "true")
    User userDtoToUser(UserRegisterDTO userRegisterDTO);

    Offer offerDtoToOffer(AddOfferDto addOfferDto);
}
