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

    public static DeliveryDest from(List row, RowHeader rowHeader) {
        return DeliveryDest.builder()
                .toName((String) row.get(rowHeader.getIndex(HeadName.TO_NAME)))
                .phoneNumber((String) row.get(rowHeader.getIndex(HeadName.TO_PHONE_NUMBER)))
                .address((String) row.get(rowHeader.getIndex(HeadName.TO_ADDRESS)))
                .wantedDate((String) row.get(rowHeader.getIndex(HeadName.WANTED_DATE)))
                .build();
    }
}
