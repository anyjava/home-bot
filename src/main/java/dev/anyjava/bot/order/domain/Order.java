package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
public class Order {

    private Long rowId;
    private String name;
    private String phoneNumber;
    private List<OrderItem> items;
    private DeliveryDest deliveryDest;
    private String memo;
    private String memo2;
    private OrderStatus status;
    private DeliveryInvoice deliveryInvoice;

    @Builder
    public Order(Long rowId,
                 String name,
                 String phoneNumber,
                 List<OrderItem> items,
                 DeliveryDest deliveryDest,
                 String memo,
                 String memo2,
                 OrderStatus status,
                 DeliveryInvoice deliveryInvoice) {
        this.rowId = rowId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.items = items;
        this.deliveryDest = deliveryDest;
        this.memo = memo;
        this.memo2 = memo2;
        this.status = status;
        this.deliveryInvoice = deliveryInvoice;
    }

    public boolean acceptedOrder() {
        return Strings.isNotEmpty(phoneNumber);
    }

    public String getStatusStr() {
        return this.status.getDesc();
    }

    public BigDecimal getTotalPrice() {
        return this.items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isShipping() {
        return this.getStatus() == OrderStatus.SHIPPING;
    }
}

