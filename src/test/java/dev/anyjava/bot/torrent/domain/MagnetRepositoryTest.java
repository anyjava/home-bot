package dev.anyjava.bot.torrent.domain;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.service.TorrentMagnetIndexer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class MagnetRepositoryTest extends TestSupport {

    @Autowired
    private MagnetRepository magnetRepository;

    @MockBean
    private TorrentMagnetIndexer torrentMagnetIndexer;

    @Test
    void test_findTopByOrderByIdDesc() {
        magnetRepository.findTopByTypeOrderByIdDesc(ProgramType.DRAMA);
    }
}