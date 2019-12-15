package dev.anyjava.bot.infra.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Stock {

    private String code;
    private String name;
    private BigDecimal currentPrice;

    @Builder
    public Stock(String code,
                 String name,
                 BigDecimal currentPrice) {
        this.code = code;
        this.name = name;
        this.currentPrice = currentPrice;
    }
}
