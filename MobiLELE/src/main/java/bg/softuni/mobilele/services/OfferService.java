package bg.softuni.mobilele.services;

import bg.softuni.mobilele.config.Mapper;
import bg.softuni.mobilele.models.dtos.AddOfferDto;
import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.repositories.ModelRepository;
import bg.softuni.mobilele.repositories.OfferRepository;
import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final Mapper mapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelRepository modelRepository;



    public OfferService(OfferRepository offerRepository, Mapper mapper, UserRepository userRepository, CurrentUser currentUser, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDto addOfferDto){
        Offer offer = this.mapper.offerDtoToOffer(addOfferDto);
        User user = userRepository.findByEmail(currentUser.getEmail()).orElse(null);
        Model model = modelRepository.findById(addOfferDto.getModelId()).orElseThrow();
        offer.setModel(model);
        offer.setUser(user);
        offerRepository.save(offer);
    }
}
