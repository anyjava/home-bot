package dev.anyjava.bot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class OrderPagerResponse {

    private final String orderName;
    private final BigDecimal totalPrice;
    private final List<OrderPagerDTO> orders;

    public int getTotalOrderCount() {
        return this.orders.size();
    }
}
