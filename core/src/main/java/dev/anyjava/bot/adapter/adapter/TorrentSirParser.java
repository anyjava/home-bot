package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TorrentSirParser implements TorrentParser {

    @Override
    public List<MagnetDTO> findAllList(Document element) {
        return element.getElementsByClass("item-subject").stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private MagnetDTO convertDTO(Element element) {
        return MagnetDTO.builder()
                .url(element.attr("href"))
                .title(element.html())
                .wrId(parseWrId(element.attr("href")))
                .build();
    }

    private Long parseWrId(String url) {
        String[] strings = url.split("wr_id=");
        return Long.parseLong(strings[strings.length-1]);
    }

    @Override
    public Magnet parseDetail(Magnet magnet, String content) {
        Document doc = Jsoup.parseBodyFragment(content);
        String magnetUrl = doc.getElementsByClass("fa-magnet").get(0)
                .siblingElements().get(0).attr("href");

        magnet.setValue(magnetUrl);
        return magnet;
    }
}
