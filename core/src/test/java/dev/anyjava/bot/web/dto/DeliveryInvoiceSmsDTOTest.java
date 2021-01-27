package dev.anyjava.bot.web.dto;

import dev.anyjava.bot.order.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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

        Order order = Order.builder()
                .name("아이유")
                .phoneNumber("01012341234")
                .deliveryInvoice(deliveryInvoice)
                .deliveryDest(dest)
                .items(items)
                .build();

        // when
        DeliveryInvoiceSmsDTO smsDTO = DeliveryInvoiceSmsDTO.from(order);

        // then
        assertThat(smsDTO.getMessage())
                .contains("아이유")
                .contains("로젠")
                .contains("123123")
                .contains("절약형 1, 실속형 2")
                .contains("https://www.ilogen.com/web/personal/trace/123123")
                .contains("유인나 님에게 ");

        log.info("message={}", smsDTO.getMessage());
    }
}