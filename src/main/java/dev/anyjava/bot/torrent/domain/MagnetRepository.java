package dev.anyjava.bot.torrent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MagnetRepository extends JpaRepository<Magnet, Long> {
    Optional<Magnet> findTopByTypeOrderByIdDesc(ProgramType type);

    List<Magnet> findAllByTypeOrderByIdDesc(ProgramType type);
}
