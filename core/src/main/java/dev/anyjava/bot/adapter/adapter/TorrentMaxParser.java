package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TorrentMaxParser implements TorrentParser {

    @Override
    public List<MagnetDTO> findAllList(Document element) {
        return element.getElementsByClass("item-subject").stream()
                .skip(1) // title row
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private MagnetDTO convertDTO(Element element) {
        return MagnetDTO.builder()
                .url(element.attr("href").replaceAll("https://torrentmax.net", ""))
                .title(element.text())
                .build();
    }

    @Override
    public Magnet parseDetail(Magnet magnet, String content) {
        Document doc = Jsoup.parseBodyFragment(content);
        String s = doc.getElementsByClass("fa-magnet")
                .get(1)
                .parent()
                .getElementsByTag("a")
                .attr("href");

        magnet.setValue(s);
        return magnet;
    }
}
