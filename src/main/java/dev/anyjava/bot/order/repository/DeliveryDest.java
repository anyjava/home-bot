package dev.anyjava.bot.order.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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

    public static DeliveryDest from(List row) {
        return DeliveryDest.builder()
                .toName((String) row.get(7))
                .phoneNumber((String) row.get(8))
                .address((String) row.get(6))
                .wantedDate((String) row.get(9))
                .build();
    }
}
