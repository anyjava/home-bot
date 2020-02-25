package dev.anyjava.bot.torrent.service;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.MagnetRepository;
import dev.anyjava.bot.torrent.domain.ProgramType;
import dev.anyjava.bot.torrent.domain.SiteType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TorrentScrapingService {

    private static final SiteType TARGET_SITE = SiteType.MAX;

    private final TorrentMagnetIndexer magnetIndexer;
    private final MagnetRepository magnetRepository;

    @Transactional
    public void run() {
        Stream.of(ProgramType.getValues())
                .map(this::getLatestMagnet)
                .map(magnetIndexer::findMagnetList)
                .flatMap(Collection::stream)
                .filter(v -> v.getType().getFilter().test(v))
                .map(magnetIndexer::findMagnetUrl)
                .forEach(magnetRepository::save);
    }

    private Magnet getLatestMagnet(ProgramType type) {
        return magnetRepository.findTopByTypeAndSiteTypeOrderByIdDesc(type, TARGET_SITE)
                .orElse(Magnet.nullOf(type));
    }
}
