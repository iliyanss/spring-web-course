package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

    @GetMapping("/offers/all")
    public String offersAll() {
        return "offers";
    }
    @GetMapping("/offers/add")
    public String offersAdd(Model model) {
        return "offer-add";
    }
}
