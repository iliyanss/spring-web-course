package bg.softuni.buildershop.service;

import bg.softuni.buildershop.config.ForexApiConfig;
import bg.softuni.buildershop.model.dto.ExRatesDTO;
import bg.softuni.buildershop.model.entity.ExRateEntity;
import bg.softuni.buildershop.repository.ExRateRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ExRateService {
    private final ExRateRepository exRateRepository;
    private final RestClient restClient;
    private final ForexApiConfig forexApiConfig;

    public ExRateService(ExRateRepository exRateRepository,
                         RestClient restClient,
                         ForexApiConfig forexApiConfig) {
        this.exRateRepository = exRateRepository;
        this.restClient = restClient;
        this.forexApiConfig = forexApiConfig;
    }


    public boolean hasInitializedExRates() {
        return this.exRateRepository.count() > 0;
    }

    public ExRatesDTO fetchExRates() {
        return this.restClient
                .get()
                .uri(forexApiConfig.getUrl(), forexApiConfig.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ExRatesDTO.class);
    }

    public void updateRates(ExRatesDTO exRates) {
        if (!forexApiConfig.getBase().equals(exRates.base())) {
            throw new IllegalArgumentException("The exchange rates that should be updated are not base on "
                    + forexApiConfig.getBase() + " but rather on " + exRates.base());
        }
        exRates.rates().forEach((currency, rate) -> {
            var exRateEntity = exRateRepository
                    .findByCurrency(currency)
                    .orElseGet(() -> new ExRateEntity().setRate(rate).setCurrency(currency));
            exRateEntity.setRate(rate);
            exRateRepository.save(exRateEntity);
        });
    }

    public Optional<BigDecimal> findExRate(String from, String to) {
        if (from.equals(to)) {
            return Optional.of(BigDecimal.ONE);
        }

        Optional<BigDecimal> fromOpt =
                forexApiConfig.getBase().equals(from)
                        ? Optional.of(BigDecimal.ONE)
                        : exRateRepository.findByCurrency(from)
                        .map(ExRateEntity::getRate);
        Optional<BigDecimal> toOpt =
                forexApiConfig.getBase().equals(to)
                        ? Optional.of(BigDecimal.ONE)
                        : exRateRepository.findByCurrency(to)
                        .map(ExRateEntity::getRate);
        if (fromOpt.isEmpty() || toOpt.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(toOpt.get().divide(fromOpt.get(), 2, RoundingMode.HALF_DOWN));
        }
    }

    public BigDecimal convert(String from, String to, BigDecimal amount) {
        return findExRate(from, to)
                .orElseThrow(() -> new
                        ObjectNotFoundException("Convertion from " + from + " to " + to + "not possible!"))
                .multiply(amount);

    }

    public List<String> allSupportedRates() {
        return exRateRepository.findAll().stream().map(ExRateEntity::getCurrency).toList();
    }
}
