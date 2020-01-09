package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class DeliveryDest {

    private String toName;
    private String address;
    private String phoneNumber;
    private String wantedDate;
    private LocalDate deliveryStartDate;

    @Builder
    public DeliveryDest(String toName, String address, String phoneNumber, String wantedDate, LocalDate deliveryStartDate) {
        this.toName = toName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.wantedDate = wantedDate;
        this.deliveryStartDate = deliveryStartDate;
    }

}
