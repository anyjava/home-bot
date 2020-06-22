package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import dev.anyjava.bot.torrent.service.TorrentMagnetIndexer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TorrentSirIndexer implements TorrentMagnetIndexer {


    private static final Map<ProgramType, String> TYPE_PARAMETERS = Map.of(
            ProgramType.ENT, "entertain",
            ProgramType.DRAMA, "drama"
//            ,
//            ProgramType.MOVIE, "torrent_movie"
    );

    private final TorrentSirClient torrentMaxClient;
    private final TorrentSirParser torrentMaxParser;

    @Override
    public List<Magnet> findMagnetList(Magnet latestMagnet) {
        String html = torrentMaxClient.getList(TYPE_PARAMETERS.get(latestMagnet.getType()));
        Document doc = Jsoup.parseBodyFragment(html);
        return torrentMaxParser.findAllList(doc).stream()
                .filter(v -> v.getWrId() > latestMagnet.getWrId())
                .map(v -> v.toMagnet(latestMagnet.getType()))
                .filter(v -> v.getType().getFilter().test(v))
                .map(this::findMagnetUrl)
                .collect(Collectors.toList());
    }

    private Magnet findMagnetUrl(Magnet magnet) {
        String html = torrentMaxClient.getDetail(TYPE_PARAMETERS.get(magnet.getType()), magnet.getWrId());
        return torrentMaxParser.parseDetail(magnet, html);
    }

}
