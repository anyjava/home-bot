package dev.anyjava.bot.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.anyjava.bot.order.domain.DepositType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class SalesSummaryResponse {
    private final Map<DepositType, SalesSummaryDTO> summary;

    @JsonProperty("totalAmount")
    public BigDecimal getTotalAmount() {
        return summary.keySet().stream()
                .map(key -> summary.get(key))
                .map(SalesSummaryDTO::getEarningAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

