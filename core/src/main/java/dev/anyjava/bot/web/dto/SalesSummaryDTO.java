package dev.anyjava.bot.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.anyjava.bot.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SalesSummaryDTO {

    private BigDecimal salesAmount;
    private BigDecimal discountAmount;

    public static SalesSummaryDTO from(Order order) {
        return SalesSummaryDTO.builder()
                .salesAmount(order.getTotalPrice())
                .discountAmount(order.getDiscountAmount())
                .build();
    }

    public static SalesSummaryDTO zero() {
        return SalesSummaryDTO.builder()
                .salesAmount(BigDecimal.ZERO)
                .discountAmount(BigDecimal.ZERO)
                .build();
    }

    @JsonProperty("earningAmount")
    public BigDecimal getEarningAmount() {
        return salesAmount.subtract(discountAmount);
    }

    public SalesSummaryDTO add(SalesSummaryDTO other) {
        return SalesSummaryDTO.builder()
                .salesAmount(this.getSalesAmount().add(other.getSalesAmount()))
                .discountAmount(this.getDiscountAmount().add(other.getDiscountAmount()))
                .build();
    }
}
