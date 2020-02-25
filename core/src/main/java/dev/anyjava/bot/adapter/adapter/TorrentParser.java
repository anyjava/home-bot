package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import org.jsoup.nodes.Document;

import java.util.List;

public interface TorrentParser {
    List<MagnetDTO> findAllList(Document element);

    Magnet parseDetail(Magnet magnet, String content);
}
