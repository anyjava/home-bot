package dev.anyjava.bot.adapter.delivery;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;

@Slf4j
class DeliveryLogenRepositoryTest extends TestSupport {

    @Autowired
    DeliveryLogenRepository deliveryLogenRepository;

    @Test
    void getDeliveryInfo() {
        byte[] bytes = deliveryLogenRepository.getDeliveryInfo("slipno", "129-4163-9340" .replace("-", ""));
        String slipno = new String(bytes, 0, bytes.length, Charset.forName("euc-kr"));

        log.info("result => {}", slipno);
    }
}