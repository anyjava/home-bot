package dev.anyjava.bot.stock.service;

import dev.anyjava.bot.stock.domain.Stock;
import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StockApiAdaptorTest extends TestSupport {

    @Autowired
    private StockApiAdaptor stockApiAdaptor;

    @Test
    public void testFindByCode() {
        Stock stock = stockApiAdaptor.findByCode("035420");

        assertThat(stock.getName()).isEqualTo("NAVER보통주");
        assertThat(stock.getCurrentPrice()).isGreaterThan(BigDecimal.ZERO);
    }
}