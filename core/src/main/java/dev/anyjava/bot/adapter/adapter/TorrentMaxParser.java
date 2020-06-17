package dev.anyjava.bot.adapter.adapter;

import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TorrentMaxParser implements TorrentParser {

    private static final Map<ProgramType, String> TYPE_PARAMETERS = Map.of(
            ProgramType.ENT, "VARIETY",
            ProgramType.DRAMA, "DRAMA"
//            ,
//            ProgramType.MOVIE, "torrent_movie"
    );

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public List<MagnetDTO> findAllList(Document element) {
        return element.getElementsByClass("item-subject").stream()
                .skip(1) // title row
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    private MagnetDTO convertDTO(Element element) {
        return MagnetDTO.builder()
                .url(element.attr("href").replaceAll("https://torrentmax.co", ""))
                .title(element.text())
                .build();
    }

    @Override
    public Magnet parseDetail(Magnet magnet, String content) {

        String requestURL = String.format("https://torrentmax.co/link?bo_table=%s&wr_id=%d&no=1",
                                          TYPE_PARAMETERS.get(magnet.getType()), magnet.getWrId());
        Request request = new Request.Builder()
                .url(requestURL)
                .build(); //GET Request

        try {
            //동기 처리시 execute함수 사용
            String url = client.newCall(request).execute().header("Location");
            magnet.setValue(url);
            return magnet;
        } catch (IOException e) {
            log.error("failed to get magnet, {}", e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}
