package dev.anyjava.bot.order.service;

import dev.anyjava.bot.adapter.delivery.DeliveryQueryServiceImpl;
import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryQueryServiceImplTest extends TestSupport {

    @Autowired
    private DeliveryQueryServiceImpl deliveryQueryService;

    @Test
    void findByTrackId() {
        DeliveryInvoice deliveryInvoice = DeliveryInvoice.builder()
                .invoiceNumber("129-4163-9340")
                .build();
        assertThat(deliveryQueryService.findByTrackId(deliveryInvoice)).isEqualTo(DeliveryStatus.DONE);
    }

    @Test
    void testFindStatus() {
        DeliveryInvoice deliveryInvoice = DeliveryInvoice.builder()
                .company("롯데")
                .invoiceNumber("101937981994")
                .build();
        DeliveryStatus status = deliveryQueryService.findStatus(deliveryInvoice);

        assertThat(status).isEqualTo(DeliveryStatus.DONE);
    }
}