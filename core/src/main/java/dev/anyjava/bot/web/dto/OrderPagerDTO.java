package dev.anyjava.bot.web.dto;

import dev.anyjava.bot.order.domain.DeliveryCompanyType;
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

    private static final String STAR_STR = "***-****-";

    private String orderName;
    private String maskedPhoneNumber;
    private List<OrderItem> items;
    private OrderStatus status;
    private String memo;

    private String deliveryName;
    private String deliveryMaskedPhoneNumber;
    private String deliveryAddress;
    private DeliveryCompanyType deliveryCompanyType;
    private String deliveryInvoice;
    private String traceUrl;

    public static OrderPagerDTO of(Order order) {
        return OrderPagerDTO.builder()
                .orderName(order.getName())
                .maskedPhoneNumber(STAR_STR + getMaskedPhoneNumber(order.getPhoneNumber()))
                .status(order.getStatus())
                .memo(order.getMemo())
                .items(order.getItems())
                .deliveryName(order.getDeliveryDest().getToName())
                .deliveryAddress(order.getDeliveryDest().getAddress())
                .deliveryCompanyType(getDeliveryCompanyType(order))
                .deliveryInvoice(order.getDeliveryInvoice().getFullyString())
                .traceUrl(order.getDeliveryTraceUrl())
                .deliveryMaskedPhoneNumber(STAR_STR + getMaskedPhoneNumber(order.getDeliveryDest().getPhoneNumber()))
                .build();
    }

    private static DeliveryCompanyType getDeliveryCompanyType(Order order) {
        return order.getDeliveryInvoice().getDeliveryCompanyType().orElse(DeliveryCompanyType.NONE);
    }

    private static String getMaskedPhoneNumber(String phoneNumber) {
        return phoneNumber.substring(phoneNumber.length() - 4);
    }

    public String getStatusStr() {
        return this.status.getDesc();
    }
}
