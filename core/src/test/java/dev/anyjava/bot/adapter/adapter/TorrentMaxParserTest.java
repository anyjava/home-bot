package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.Magnet;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TorrentMaxParserTest extends TestSupport {

    @Autowired
    TorrentMaxClient torrentMaxClient;

    @Autowired
    TorrentMaxParser torrentMaxParser;

    @Test
    void findAllList() {
        String html = torrentMaxClient.getList("VARIETY");
        Document doc = Jsoup.parseBodyFragment(html);

        List<MagnetDTO> dtos = torrentMaxParser.findAllList(doc);

        log.info("dtos = {}", dtos);
    }

    @Test
    void parseDetail() {
        Magnet magnet = new Magnet();
        String html = torrentMaxClient.getMagnet("VARIETY", 12408);

        torrentMaxParser.parseDetail(magnet, html);

        assertThat(magnet.getValue()).isEqualTo("magnet:?xt=urn:btih:2ea6a37b41640417207320c4ec716478dc762b38");
    }

}