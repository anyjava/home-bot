package dev.anyjava.bot.web.dto;

import dev.anyjava.bot.order.domain.DeliveryDest;
import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.OrderItem;
import dev.anyjava.bot.order.domain.OrderItemType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryInvoiceSmsDTOTest {

    @Test
    public void buildMessage() {
        DeliveryInvoice deliveryInvoice = DeliveryInvoice.builder()
                .company("로젠")
                .invoiceNumber("123123")
                .build();

        List<OrderItem> items = List.of(
                OrderItem.builder()
                        .type(OrderItemType.NO_1)
                        .quantity(1)
                        .build(),
                OrderItem.builder()
                        .type(OrderItemType.NO_2)
                        .quantity(2)
                        .build()
        );

        DeliveryDest dest = DeliveryDest.builder()
                .toName("유인나")
                .build();
        DeliveryInvoiceSmsDTO smsDTO = DeliveryInvoiceSmsDTO.from("아이유", "01012341234", deliveryInvoice, dest, items);

        assertThat(smsDTO.getMessage())
                .contains("아이유")
                .contains("로젠")
                .contains("123123")
                .contains("절약형 1, 실속형 2")
                .contains("유인나 님에게 ");
    }
}