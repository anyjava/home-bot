package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class TorrentMaxClientTest extends TestSupport {

    @Autowired
    TorrentMaxClient client;

    @Test
    void getList() {
        log.info("RES => {}", client.getList("VARIETY"));
    }
}