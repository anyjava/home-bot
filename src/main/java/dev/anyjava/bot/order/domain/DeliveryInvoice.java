package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DeliveryInvoice {
    private String company;
    private String invoiceNumber;

    @Builder
    public DeliveryInvoice(String company, String invoiceNumber) {
        this.company = company;
        this.invoiceNumber = invoiceNumber;
    }
}
