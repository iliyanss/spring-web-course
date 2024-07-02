package bg.softuni.mobilele.models.dtos;

import java.math.BigDecimal;

public record ConvertionResultDTO(String from, String to, BigDecimal amount, BigDecimal result) {
}
