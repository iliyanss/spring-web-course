package bg.softuni.buildershop.model.dto;

import java.math.BigDecimal;

public record ConvertionResultDTO(String from, String to, BigDecimal amount, BigDecimal result) {
}
