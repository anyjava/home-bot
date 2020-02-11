package dev.anyjava.bot.stock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockNoti {

    private final String code;
    private final String name;

    private StockNoti(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StockNoti of(String code, String name) {
        return new StockNoti(code, name);
    }
}
