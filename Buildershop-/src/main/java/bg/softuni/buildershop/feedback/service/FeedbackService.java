package bg.softuni.buildershop.feedback.service;

import bg.softuni.buildershop.feedback.model.dto.AddFeedbackDTO;
import bg.softuni.buildershop.feedback.model.entity.FeedBackEntity;
import bg.softuni.buildershop.feedback.repository.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }

    public void addFeedback(AddFeedbackDTO addFeedbackDTO){
        FeedBackEntity feedBackEntity = this.modelMapper.map(addFeedbackDTO, FeedBackEntity.class);
        this.feedbackRepository.save(feedBackEntity);
    }
    @Transactional
    public void deleteFeedback(Long id){
        this.feedbackRepository.deleteById(id);
    }
    public Optional<AddFeedbackDTO>getById(Long id){
        Optional<FeedBackEntity> feedBackEntityById = this.feedbackRepository.findById(id);
        if(feedBackEntityById.isPresent()){
            FeedBackEntity feedBackEntity = feedBackEntityById.get();
            AddFeedbackDTO addFeedbackDTO = this.modelMapper.map(feedBackEntity, AddFeedbackDTO.class);
            return Optional.of(addFeedbackDTO);
        }
        return Optional.empty();
    }
    public List<AddFeedbackDTO>getAllFeedBacks(){
        return this.feedbackRepository
                .findAll()
                .stream()
                .map(feedBackEntity -> this.modelMapper.map(feedBackEntity, AddFeedbackDTO.class))
                .toList();
    }
}
