package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.torrent.domain.ProgramType;
import dev.anyjava.bot.torrent.service.TorrentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TorrentController {

    private final TorrentQueryService torrentQueryService;

    @GetMapping(value = "/torrents/by-type")
    public String getAll(@RequestParam ProgramType type) {
        return torrentQueryService.getAll(type).toString();
    }
}
