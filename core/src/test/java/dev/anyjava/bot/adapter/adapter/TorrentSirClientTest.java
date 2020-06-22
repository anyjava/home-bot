package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Disabled("api test 라 ignore")
@Slf4j
class TorrentSirClientTest extends TestSupport {

    @Autowired
    private TorrentSirClient torrentSirClient;

    @Test
    void get() {
        String html = torrentSirClient.getList("entertain");
        log.info("{}", html);

        Document doc = Jsoup.parseBodyFragment(html);
    }

    @Test
    void getDetail() {
        String entertain = torrentSirClient.getDetail("entertain", 14634L);

        log.info(">> {}", entertain);
    }
}