package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.AddOfferDTO;
import bg.softuni.mobilele.models.dtos.OfferDetailsDTO;
import bg.softuni.mobilele.models.dtos.OfferSummaryDTO;
import bg.softuni.mobilele.models.entities.OfferEntity;

import bg.softuni.mobilele.models.entities.TransmissionEntity;
import bg.softuni.mobilele.repositories.OfferRepository;
import bg.softuni.mobilele.repositories.TransmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final TransmissionRepository transmissionRepository;
    private final ExRateService exRateService;

    public OfferService(OfferRepository offerRepository, TransmissionRepository transmissionRepository, ExRateService exRateService) {
        this.offerRepository = offerRepository;
        this.transmissionRepository = transmissionRepository;
        this.exRateService = exRateService;
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
                .map(this::toOfferDetails)
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
                offerEntity.getEngine(),
                offerEntity.getTransmission().getName());
    }


    private OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
        // todo use mapping library
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine(),
                offerEntity.getYear(),
                offerEntity.getTransmission().getName(),
                offerEntity.getImageUrl(),
                offerEntity.getPrice(),
                exRateService.allSupportedRates());
    }

    private OfferEntity map(AddOfferDTO addOfferDTO) {
        // todo - use mapped (e.g. ModelMapper)
        TransmissionEntity transmission = transmissionRepository.findByName(addOfferDTO.transmission());
        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setEngine(addOfferDTO.engineType())
                .setMileage(addOfferDTO.mileage())
                .setPrice(addOfferDTO.price())
                .setYear(addOfferDTO.year())
                .setImageUrl(addOfferDTO.imageUrl())
                .setTransmission(transmission);
    }
}
