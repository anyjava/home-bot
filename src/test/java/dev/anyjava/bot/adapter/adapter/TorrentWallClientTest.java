package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore("api test 라 ignore")
@Slf4j
class TorrentWallClientTest extends TestSupport {

    @Autowired
    private TorrentWallClient torrentWallClient;

    @Test
    void get() {
        String html = torrentWallClient.getList("torrent_variety");
        log.info("{}", html);

        Document doc = Jsoup.parseBodyFragment(html);
    }

    @Test
    void getMagnet() {
        String magnet = torrentWallClient.getMagnet("torrent_variety", 902651L, "0", "yes");
        log.info("{}", magnet);

        assertThat(magnet).isNotEmpty();
    }
}