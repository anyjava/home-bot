package dev.anyjava.bot.order.service;

import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.order.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class DeliveryQueryService {

    private static final String GUBUN = "slipno";
    private static final Charset CHARSET = Charset.forName("euc-kr");

    private final DeliveryRepository deliveryRepository;

    public DeliveryStatus findByTrackId(DeliveryInvoice deliveryInvoice) {
        byte[] bytes = deliveryRepository.getDeliveryInfo(GUBUN, deliveryInvoice.getInvoiceNumberWithoutDash());
        String html = new String(bytes, 0, bytes.length, CHARSET);

        if (html.contains(DeliveryStatus.DONE.getParsingText())) {
            return DeliveryStatus.DONE;
        }

        if (html.contains(DeliveryStatus.READY.getParsingText())) {
            return DeliveryStatus.READY;
        }

        return DeliveryStatus.NONE;
    }
}
