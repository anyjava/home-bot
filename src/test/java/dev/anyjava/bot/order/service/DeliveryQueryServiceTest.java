package dev.anyjava.bot.order.service;

import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryQueryServiceTest extends TestSupport {

    @Autowired
    private DeliveryQueryService deliveryQueryService;

    @Test
    void findByTrackId() {
        DeliveryInvoice deliveryInvoice = DeliveryInvoice.builder()
                .invoiceNumber("129-4163-9340")
                .build();
        assertThat(deliveryQueryService.findByTrackId(deliveryInvoice)).isEqualTo(DeliveryStatus.DONE);
    }
}