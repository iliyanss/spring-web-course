package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.AddFeedbackDTO;
import bg.softuni.buildershop.model.dto.AddProductDTO;
import bg.softuni.buildershop.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback")
    public String addProduct() {
        return "feedback";
    }
    @PostMapping("/feedback")
    public String feedback(@Valid AddFeedbackDTO addFeedbackDTO,
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
    @ModelAttribute("addFeedbackDTO")
    public AddFeedbackDTO AddFeedbackDTO() {
        return new AddFeedbackDTO();
    }
}
