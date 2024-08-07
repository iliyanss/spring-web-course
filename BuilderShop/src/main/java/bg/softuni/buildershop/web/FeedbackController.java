package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.AddFeedbackDTO;
import bg.softuni.buildershop.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    @ModelAttribute("addFeedbackDTO")
    public AddFeedbackDTO AddFeedbackDTO() {
        return new AddFeedbackDTO();
    }
    @GetMapping("/feedback")
    public String addProduct() {
        return "feedback";
    }
    @PostMapping("/feedback")
    public String createFeedback(@Valid AddFeedbackDTO addFeedbackDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("addFeedbackDTO", addFeedbackDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addFeedbackDTO", bindingResult);

            return "redirect:/feedback";
        }
        this.feedbackService.createFeedback(addFeedbackDTO);
        return "redirect:/home";
    }

    @GetMapping("/feedback/all")
    public String getAllMessages(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllMessages());
        return "all-feedbacks";
    }

    @DeleteMapping("/feedback/remove/{id}")
    public String removeFeedback(@PathVariable Long id) {
        feedbackService.removeFeedback(id);
        return "redirect:/feedback/all";
    }
}
