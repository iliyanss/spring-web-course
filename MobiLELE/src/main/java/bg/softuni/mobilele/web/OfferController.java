package bg.softuni.mobilele.web;

import bg.softuni.mobilele.models.dtos.AddOfferDTO;
import bg.softuni.mobilele.models.entities.TransmissionEntity;
import bg.softuni.mobilele.models.enums.EngineTypeEnum;
import bg.softuni.mobilele.repositories.TransmissionRepository;
import bg.softuni.mobilele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private TransmissionRepository transmissionRepository;

    public OfferController(OfferService offerService, TransmissionRepository transmissionRepository) {
        this.offerService = offerService;
        this.transmissionRepository = transmissionRepository;
    }

    @ModelAttribute("allEngineTypes")
    public EngineTypeEnum[] allEngineTypes() {
        return EngineTypeEnum.values();
    }

    @GetMapping("/add")
    public String newOffer(Model model) {

        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", AddOfferDTO.empty());
        }

        return "offer-add";
    }

    @PostMapping("add")
    public String createOffer(
            @Valid AddOfferDTO addOfferDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }


        long newOfferId = offerService.createOffer(addOfferDTO);

        return "redirect:/offers/" + newOfferId;
    }

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable("id") Long id,
                               Model model) {

        model.addAttribute("offerDetails", offerService.getOfferDetails(id));

        return "details";
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
