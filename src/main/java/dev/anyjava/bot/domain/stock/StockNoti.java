package dev.anyjava.bot.domain.stock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockNoti {
    private String code;

    private StockNoti(String code) {
        this.code = code;
    }

    public static StockNoti of(String code) {
        return new StockNoti(code);
    }
}
