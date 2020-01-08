package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.domain.OrderItem;
import dev.anyjava.bot.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class OrderPagerDTO {

    private String maskedPhoneNumber;
    private List<OrderItem> items;
    private OrderStatus status;
    private String memo;

    private String deliveryName;
    private String deliveryAddress;
    private String deliveryInvoice;

    public static OrderPagerDTO of(Order order) {
        return OrderPagerDTO.builder()
                .maskedPhoneNumber("**********" + order.getPhoneNumber().substring(order.getPhoneNumber().length() - 4))
                .status(order.getStatus())
                .memo(order.getMemo())
                .items(order.getItems())
                .deliveryName(order.getDeliveryDest().getToName())
                .deliveryAddress(order.getDeliveryDest().getAddress())
                .deliveryInvoice("") // TODO: cuddy.son 2020-01-8 필드 매핑 필요
                .build();
    }

    public String getStatusStr() {
        return this.status.getDesc();
    }
}
