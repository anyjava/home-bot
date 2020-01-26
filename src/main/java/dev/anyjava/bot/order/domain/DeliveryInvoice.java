package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
public class DeliveryInvoice {

    private String company;
    @Getter
    private String invoiceNumber;

    @Builder
    public DeliveryInvoice(String company, String invoiceNumber) {
        this.company = company;
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumberWithoutDash() {
        return invoiceNumber.replace("-", "");
    }

    public DeliveryCompanyType getDeliveryCompanyType() {
        return DeliveryCompanyType.MAP_BY_KOREAN_NAME.get(this.company);
    }

    public String getCompanyName() {
        return this.getDeliveryCompanyType().getKoreanName();
    }
}
