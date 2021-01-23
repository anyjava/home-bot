package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Magnet magnet = Magnet.nullOf(ProgramType.ENT);
//        ReflectionTestUtils.setField(magnet, "wrId", 12585);
        String html = "";

        torrentMaxParser.parseDetail(magnet, html);

        assertThat(magnet.getValue()).isEqualTo("magnet:?xt=urn:btih:8c16cf0c2ee357686c264bdfc8ace5e4bf6a9f96");
    }

}