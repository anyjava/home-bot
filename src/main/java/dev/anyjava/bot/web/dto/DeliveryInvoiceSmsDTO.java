package dev.anyjava.bot.web.dto;

import dev.anyjava.bot.order.domain.DeliveryDest;
import dev.anyjava.bot.order.domain.DeliveryInvoice;
import dev.anyjava.bot.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class DeliveryInvoiceSmsDTO {

    private static final String FROM_NUMBER = "01040193228";
    private static final String SMS_MESSAGE_FORMAT =
            "_name_님, 주문하신 반건시 곶감(_items_)이 _office_택배로 _toName_발송되었습니다. 송장번호: _invoiceNumber_ 감사합니다. :-)";
    private final String from;
    private final String to;
    private final String message;

    @Builder
    public DeliveryInvoiceSmsDTO(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public static DeliveryInvoiceSmsDTO from(String orderName,
                                             String orderPhoneNumber,
                                             DeliveryInvoice deliveryInvoice,
                                             DeliveryDest deliveryDest,
                                             List<OrderItem> items) {
        String itemListStr = items.stream()
                .map(v -> v.getItemName() + " " + v.getQuantity())
                .distinct()
                .collect(Collectors.joining(", "));

        String toName = getToName(orderName, deliveryDest);

        return DeliveryInvoiceSmsDTO.builder()
                .from(FROM_NUMBER)
                .to(orderPhoneNumber)
                .message(
                        SMS_MESSAGE_FORMAT.replace("_name_", orderName)
                                .replace("_office_", deliveryInvoice.getCompany())
                                .replace("_invoiceNumber_", deliveryInvoice.getInvoiceNumber())
                                .replace("_items_", itemListStr)
                                .replace("_toName_", toName)
                )
                .build();
    }

    private static String getToName(String orderName, DeliveryDest deliveryDest) {
        return orderName.equals(deliveryDest.getToName()) ? "" : deliveryDest.getToName() + " 님에게 ";
    }

}
