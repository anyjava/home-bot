package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class OrderItem {

    private OrderItemType type;
    private int quantity;

    @Builder
    public OrderItem(OrderItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getItemName() {
        return this.type.getName();
    }

    public BigDecimal getPrice() {
        return this.type.getPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
    }

}

