package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TorrentWalParser {

    public List<MagnetDTO> findAllList(Document element) {
        return element.getElementsByClass("list_subject").stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private MagnetDTO convertDTO(Element element) {
        return MagnetDTO.builder()
                .url(element.attr("href"))
                .title(element.html())
                .build();
    }

    public Magnet parseDetail(Magnet magnet, String content) {
        StringTokenizer tokenizer = new StringTokenizer(content, System.lineSeparator());
        while (tokenizer.hasMoreTokens()) {
            String line = tokenizer.nextToken();
            if (line.contains("magnet:")) {
                magnet.setValue(line.split("'")[1].split("&")[0]);
            }
        }
        return magnet;
    }
}
