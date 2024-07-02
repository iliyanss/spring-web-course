package bg.softuni.mobilele.init;

import bg.softuni.mobilele.models.entities.ExRateEntity;
import bg.softuni.mobilele.repositories.ExRateRepository;
import bg.softuni.mobilele.services.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ExchangeRateInitializer implements CommandLineRunner {
    private final ExRateService exRateService;

    public ExchangeRateInitializer(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!exRateService.hasInitializedExRates()) {
            exRateService.updateRates(
                    exRateService.fetchExRates()
            );
        }
    }
}
