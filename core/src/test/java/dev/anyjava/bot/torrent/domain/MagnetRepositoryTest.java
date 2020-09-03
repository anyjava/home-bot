package dev.anyjava.bot.torrent.domain;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.service.TorrentMagnetIndexer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class MagnetRepositoryTest extends TestSupport {

    @Autowired
    private MagnetRepository magnetRepository;

    @MockBean
    private TorrentMagnetIndexer torrentMagnetIndexer;

    @Test
    void test_findTopByOrderByIdDesc() {
        magnetRepository.findTopByTypeAndSiteTypeOrderByIdDesc(ProgramType.DRAMA, SiteType.MAX);
    }

    @Test
    void test_findTop30OrderByIdDesc() {
        magnetRepository.findTop50ByOrderByIdDesc();
    }
}