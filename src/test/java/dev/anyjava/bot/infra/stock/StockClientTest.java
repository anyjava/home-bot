package dev.anyjava.bot.infra.stock;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class StockClientTest extends TestSupport {

    @Autowired
    private StockClient stockClient;

    @Test
    void getStock() {
        String xml = stockClient.getStock("035420");

        JSONObject json = XML.toJSONObject(xml);

        log.info("json = {}", json);
    }
}