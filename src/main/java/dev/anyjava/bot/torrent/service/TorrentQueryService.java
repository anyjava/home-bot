package dev.anyjava.bot.torrent.service;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.MagnetRepository;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TorrentQueryService {

    private final MagnetRepository magnetRepository;

    public List<Magnet> getAll(ProgramType type) {
        return magnetRepository.findAllByTypeOrderByIdDesc(type);
    }
}
