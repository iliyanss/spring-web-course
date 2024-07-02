package bg.softuni.mobilele.web;

import bg.softuni.mobilele.models.dtos.ConvertionResultDTO;
import bg.softuni.mobilele.services.ExRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class Currency {
    private final ExRateService exRateService;

    public Currency(ExRateService exRateService) {
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
