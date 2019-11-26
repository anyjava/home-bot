package dev.anyjava.bot.stock;

import dev.anyjava.bot.support.TestSupport;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class HttpBinClientTest extends TestSupport {

    @Autowired
    HttpBinClient client;

    @DisplayName("readTimeout 테스트")
    @Test
    public void testGet() {
        assertThrows(RetryableException.class, () -> client.getTimeout());
    }

    @DisplayName("500 에러 검증")
    @Test
    public void testGetError() {
        assertThrows(RetryableException.class, () -> client.getError());
    }
}