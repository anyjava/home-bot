package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.Magnet;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Disabled
@Slf4j
class TorrentSirParserTest extends TestSupport {

    @Autowired
    private TorrentSirClient torrentSirClient;

    @Autowired
    private TorrentSirParser torrentSirParser;

    @Test
    void findAll() {
        String html = torrentSirClient.getList("entertain");
        Document doc = Jsoup.parseBodyFragment(html);

        List<MagnetDTO> all = torrentSirParser.findAllList(doc);
        log.info(" >>> {}", all);

        assertAll(
                () -> assertThat(all.size()).isEqualTo(22),
                () -> assertThat(all.get(0).getTitle()).isNotEmpty(),
                () -> assertThat(all.get(0).getWrId()).isNotNull(),
                () -> assertThat(all.get(0).getUrl()).isNotEmpty()
        );
    }

    @Test
    void parseWrId() {
        String url = "https://torrentsir31.com/bbs/board.php?bo_table=entertain&wr_id=14634";
        Long wrId = 1L;

        String[] strings = url.split("wr_id=");
        wrId = Long.parseLong(strings[strings.length-1]);

        assertThat(wrId).isEqualTo(14634L);
    }

    @Test
    void parseDetail() {
        String entertain = torrentSirClient.getDetail("entertain", 14634L);

        Magnet magnet = new Magnet();
        torrentSirParser.parseDetail(magnet, entertain);

        assertThat(magnet.getValue()).isEqualTo("magnet:?xt=urn:btih:db3ee3a5166f8f2f4c8441888bfbde518545d4e9");
    }

}