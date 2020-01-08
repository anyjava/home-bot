package dev.anyjava.bot.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DeliveryDest {

    private String toName;
    private String address;
    private String phoneNumber;
    private String wantedDate;

    @Builder
    public DeliveryDest(String toName, String address, String phoneNumber, String wantedDate) {
        this.toName = toName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.wantedDate = wantedDate;
    }

}
