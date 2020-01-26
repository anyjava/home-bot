package dev.anyjava.bot.adapter.delivery;

import dev.anyjava.bot.order.domain.DeliveryCompanyType;
import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.DeliveryStatus;
import dev.anyjava.bot.order.domain.Order;
import dev.anyjava.bot.order.service.DeliveryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeliveryQueryServiceImpl implements DeliveryQueryService {

    private static final String GUBUN = "slipno";
    private static final Charset CHARSET = Charset.forName("euc-kr");

    private final DeliveryLogenRepository deliveryLogenRepository;
    private final DeliveryLotteRepository deliveryLotteRepository;

    @Override
    public Order findByTrackId(Order order) {
        DeliveryCompanyType deliveryCompanyType = order.getDeliveryInvoice().getDeliveryCompanyType();
        if (deliveryCompanyType == DeliveryCompanyType.LOGEN) {
            DeliveryStatus deliveryStatus = findByTrackId(order.getDeliveryInvoice());
            if (deliveryStatus == DeliveryStatus.DONE) {
                order.doneShipping();
            }
        } else if (deliveryCompanyType == DeliveryCompanyType.LOTTE) {
            DeliveryStatus deliveryStatus = findStatus(order.getDeliveryInvoice());
            if (deliveryStatus == DeliveryStatus.DONE) {
                order.doneShipping();
            }
        }
        return order;
    }

    public DeliveryStatus findStatus(DeliveryInvoice deliveryInvoice) {
        String html = deliveryLotteRepository.getDeliveryInfo(Map.of("InvNo", deliveryInvoice.getInvoiceNumber()));

        if (html.contains(DeliveryStatus.DONE.getParsingTextOfLotte())) {
            return DeliveryStatus.DONE;
        }

        if (html.contains(DeliveryStatus.READY.getParsingTextOfLotte())) {
            return DeliveryStatus.READY;
        }
        return DeliveryStatus.NONE;
    }

    @Override
    public DeliveryStatus findByTrackId(DeliveryInvoice deliveryInvoice) {
        byte[] bytes = deliveryLogenRepository.getDeliveryInfo(GUBUN, deliveryInvoice.getInvoiceNumberWithoutDash());
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
