package dev.anyjava.bot.torrent.service;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.MagnetRepository;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TorrentScrapingService {

    private final TorrentMagnetIndexer magnetIndexer;
    private final MagnetRepository magnetRepository;

    @Transactional
    public void run() {
        Stream.of(ProgramType.values())
                .map(this::getLatestMagnet)
                .map(magnetIndexer::findMagnetList)
                .flatMap(Collection::stream)
                .filter(v -> v.getType().getFilter().test(v))
                .map(magnetIndexer::findMagnetUrl)
                .forEach(magnetRepository::save);
    }

    private Magnet getLatestMagnet(ProgramType type) {
        return magnetRepository.findTopByTypeOrderByIdDesc(type)
                .orElse(Magnet.nullOf(type));
    }
}
