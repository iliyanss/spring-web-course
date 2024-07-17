package bg.softuni.buildershop.web;

import bg.softuni.buildershop.model.dto.ConvertionResultDTO;
import bg.softuni.buildershop.service.ExRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyRestController {
    private final ExRateService exRateService;

    public CurrencyRestController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @GetMapping("/api/convert")
    public ResponseEntity<ConvertionResultDTO> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount
    ) {
        BigDecimal result = exRateService.convert(from, to, amount);
        return ResponseEntity.ok(new ConvertionResultDTO(from, to, amount, result));
    }
}

