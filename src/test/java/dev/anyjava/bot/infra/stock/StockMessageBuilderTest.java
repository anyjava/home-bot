package dev.anyjava.bot.infra.stock;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class StockMessageBuilderTest {

    // subject
    private StockMessageBuilder messageBuilder = new StockMessageBuilder();

    @Test
    public void testBuild() {
        Stock stock = Stock.builder()
                .name("카카오")
                .currentPrice(BigDecimal.valueOf(123_000))
                .build();

        String message = messageBuilder.build(stock);

        assertThat(message).isEqualTo("아빠,엄마 카카오 오늘 주가는 123,000 원 이야");
    }
}