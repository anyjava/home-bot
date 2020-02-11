package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import dev.anyjava.bot.torrent.service.TorrentMagnetIndexer;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TorrentWalIndexer implements TorrentMagnetIndexer {

    private static final Map<ProgramType, String> TYPE_PARAMETERS = Map.of(
            ProgramType.ENT, "torrent_variety",
            ProgramType.DRAMA, "torrent_tv",
            ProgramType.MOVIE, "torrent_movie"
    );

    private final TorrentWallClient torrentWallClient;
    private final TorrentWalParser torrentWalParser;

    @Override
    public List<Magnet> findMagnetList(Magnet latestMagnet) {
        String html = torrentWallClient.getList(TYPE_PARAMETERS.get(latestMagnet.getType()));
        Document doc = Jsoup.parseBodyFragment(html);
        return torrentWalParser.findAllList(doc).stream()
                .filter(v -> v.getWrId() > latestMagnet.getWrId())
                .map(v -> v.toMagnet(latestMagnet.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public Magnet findMagnetUrl(Magnet magnet) {
        String html = torrentWallClient.getMagnet(TYPE_PARAMETERS.get(magnet.getType()), magnet.getWrId(), "0", "yes");
        return torrentWalParser.parseDetail(magnet, html);
    }

}
