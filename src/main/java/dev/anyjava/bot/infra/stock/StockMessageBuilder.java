package dev.anyjava.bot.infra.stock;

import org.springframework.stereotype.Component;

@Component
public class StockMessageBuilder {
    private static final String MESSAGE_FORMAT = "아빠,엄마 %s 오늘 주가는 %,d 원 이야";

    public String build(Stock stock) {
        return String.format(MESSAGE_FORMAT, stock.getName(), stock.getCurrentPrice().longValue());
    }
}
