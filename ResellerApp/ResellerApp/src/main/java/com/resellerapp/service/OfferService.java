package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.OfferEntity;
import com.resellerapp.model.entity.UserEntity;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ConditionService conditionService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public OfferService(OfferRepository offerRepository, ConditionService conditionService, UserService userService, UserRepository userRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }


    public void addOffer(AddOfferDTO addOfferDTO, Long id) {
        OfferEntity offerEntity = new OfferEntity();
        ConditionEntity conditionEntity = this.conditionService.findCondition(addOfferDTO.getCondition());
        UserEntity userEntity = this.userService.findUserById(id).orElse(null);
        offerEntity.setCondition(conditionEntity);
        offerEntity.setDescription(addOfferDTO.getDescription());
        offerEntity.setPrice(addOfferDTO.getPrice());
        offerEntity.setAddedBy(userEntity);
        userEntity.getOffers().add(offerEntity);
        offerRepository.save(offerEntity);
        userRepository.save(userEntity);
    }
    public List<OfferEntity>getMyOffers(Long userId) {
        UserEntity userEntity = this.userService.findUserById(userId).orElse(null);
        return this.offerRepository.findAllByAddedBy_Id(userEntity.getId());
    }
    public List<OfferEntity>getOtherOffers(Long userId){
        return this.offerRepository.findAllByAddedBy_Id_Not(userId);
    }

    public boolean removeOfferById(Long id) {
        OfferEntity offerToRemove = this.offerRepository.findById(id).orElse(null);
        if(offerToRemove != null){
            offerToRemove.getAddedBy().getOffers().remove(offerToRemove);
            userRepository.save(offerToRemove.getAddedBy());
            offerToRemove.setAddedBy(null);
            this.offerRepository.delete(offerToRemove);
            return true;
        }

        return false;
    }

    public boolean buyOfferById(Long id) {
        OfferEntity offerToBuy = this.offerRepository.findById(id).orElse(null);
        UserEntity boughtBy = this.userService.findUserById(loggedUser.getId()).orElse(null);
        offerToBuy.setBoughtBy(boughtBy);
        UserEntity boughtFrom = this.userService.findUserById(offerToBuy.getAddedBy().getId()).orElse(null);
        Set<OfferEntity> offers = boughtFrom.getOffers();
        offers.remove(offerToBuy);
        boughtFrom.setOffers(offers);
        boughtBy.getBoughtOffers().add(offerToBuy);
        this.userRepository.save(boughtFrom);
        return true;
    }
    public OfferEntity getOfferById(Long id){
        return this.offerRepository.findById(id).orElse(null);
    }
}
