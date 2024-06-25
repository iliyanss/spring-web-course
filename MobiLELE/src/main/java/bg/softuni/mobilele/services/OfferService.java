package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.AddOfferDTO;
import bg.softuni.mobilele.models.dtos.OfferDetailsDTO;
import bg.softuni.mobilele.models.dtos.OfferSummaryDTO;
import bg.softuni.mobilele.models.entities.OfferEntity;

import bg.softuni.mobilele.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public long createOffer(AddOfferDTO addOfferDTO) {
        return offerRepository.save(map(addOfferDTO)).getId();
    }

    public void deleteOffer(long orderId) {
        offerRepository.deleteById(orderId);
    }

    public OfferDetailsDTO getOfferDetails(Long id) {

        return this.offerRepository
                .findById(id)
                .map(OfferService::toOfferDetails)
                .orElseThrow();
    }

    public List<OfferSummaryDTO> getAllOffersSummary() {
        return offerRepository
                .findAll()
                .stream()
                .map(OfferService::toOfferSummary)
                .toList();
    }

    private static OfferSummaryDTO toOfferSummary(OfferEntity offerEntity) {
        // todo use mapping library
        return new OfferSummaryDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine());
    }


    private static OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
        // todo use mapping library
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine());
    }

    private static OfferEntity map(AddOfferDTO addOfferDTO) {
        // todo - use mapped (e.g. ModelMapper)
        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setEngine(addOfferDTO.engineType())
                .setMileage(addOfferDTO.mileage());
    }
}
