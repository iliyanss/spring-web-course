package bg.softuni.buildershop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public ExRateEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
