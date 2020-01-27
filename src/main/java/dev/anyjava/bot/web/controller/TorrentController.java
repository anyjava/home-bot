package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.torrent.domain.MagnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TorrentController {

    private final TorrentRssConverter torrentRssConverter;
    private final MagnetRepository magnetRepository;

    @GetMapping(value = "/torrents")
    public String getAll() {
        return torrentRssConverter.convertRss(magnetRepository.findTop30ByOrderByIdDesc());
    }
}
