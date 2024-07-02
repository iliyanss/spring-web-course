package bg.softuni.mobilele.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String currency;
    @Positive
    @Column(nullable = false)
    private BigDecimal rate;

    public ExRateEntity() {

    }

    public String getCurrency() {
        return currency;
    }

    public ExRateEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExRateEntity setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public String toString() {
        return "ExRateEntity{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
