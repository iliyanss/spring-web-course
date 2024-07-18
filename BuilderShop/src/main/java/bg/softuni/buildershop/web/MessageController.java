package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.MessageDTO;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.service.MessageService;
import bg.softuni.buildershop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    @ModelAttribute("messageDTO")
    public MessageDTO messageDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCreatedAt(LocalDateTime.now());
        return messageDTO;
    }
    @GetMapping("/contact")
    public String showContactForm() {
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(@Valid MessageDTO messageDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity currentUser = this.userService.findUserById(principalId).orElse(null);
        messageDTO.setCreatedAt(LocalDateTime.now());
        if (!messageDTO.getEmail().equals(currentUser.getEmail())) {
            bindingResult.addError(
                    new FieldError(
                            "differentEmail",
                            "email",
                            "You should use your account's email address."));
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("messageDTO", messageDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.messageDTO", bindingResult);

            return "redirect:/messages/contact";
        }
        messageService.sendMessage(messageDTO, principal);
        return "redirect:/messages/contact?success";
    }

    @GetMapping("/all-messages")
    public String getAllMessages(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "all-messages";
    }

    @DeleteMapping("/remove/{id}")
    public String removeMessage(@PathVariable Long id) {
        messageService.removeMessage(id);
        return "redirect:/messages/all-messages";
    }
}