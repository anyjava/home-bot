package dev.anyjava.bot.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum OrderItemType {
    NO_1("절약형", BigDecimal.valueOf(20_000)),
    NO_2("실속형", BigDecimal.valueOf(40_000)),
    NO_3("선물포장", BigDecimal.valueOf(55_000));

    private final String name;
    private final BigDecimal price;
}
