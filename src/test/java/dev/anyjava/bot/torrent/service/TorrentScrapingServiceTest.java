package dev.anyjava.bot.torrent.service;

import dev.anyjava.bot.support.TestSupport;
import dev.anyjava.bot.torrent.domain.MagnetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TorrentScrapingServiceTest extends TestSupport {

    @Autowired
    TorrentScrapingService torrentScrapingService;

    @Autowired
    private MagnetRepository magnetRepository;

    @Test
    void run() {
        torrentScrapingService.run();

        log.info(">> {}", magnetRepository.findAll());
    }
}