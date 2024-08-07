package bg.softuni.buildershop.feedback.web;

import bg.softuni.buildershop.feedback.model.dto.AddFeedbackDTO;
import bg.softuni.buildershop.feedback.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackRestController {
    private final FeedbackService feedbackService;

    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    @PostMapping
    public ResponseEntity<AddFeedbackDTO> createFeedback(@RequestBody AddFeedbackDTO addFeedbackDTO) {
        feedbackService.addFeedback(addFeedbackDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<AddFeedbackDTO>>getAllFeedBacks(){
        return ResponseEntity.ok(feedbackService.getAllFeedBacks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddFeedbackDTO>findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(feedbackService.getById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Feedback not found")));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AddFeedbackDTO> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }

}
