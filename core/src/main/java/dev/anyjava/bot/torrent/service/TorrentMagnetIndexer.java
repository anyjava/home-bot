package dev.anyjava.bot.torrent.service;

import dev.anyjava.bot.torrent.domain.Magnet;

import java.util.List;

public interface TorrentMagnetIndexer {
    List<Magnet> findMagnetList(Magnet latestMagnet);

    Magnet findMagnetUrl(Magnet magnet);
}
