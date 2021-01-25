package dev.anyjava.bot.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class OrderReservationDTO implements Comparable<OrderReservationDTO> {

    @EqualsAndHashCode.Include
    private final LocalDate reservationDate;

    private final long count;

    @Override
    public int compareTo(OrderReservationDTO others) {
        return this.reservationDate.compareTo(others.reservationDate);
    }
}

