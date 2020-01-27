package dev.anyjava.bot.web.controller;

import dev.anyjava.bot.torrent.domain.Magnet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TorrentRssConverter {

    public static final String TAIL = "</channel></rss>\n";
    public static final String HEADER = "<rss xmlns:showrss=\"http://showrss.info/\" version=\"2.0\"><channel>\n";

    public String convertRss(List<Magnet> magnets) {

        StringBuilder sb = new StringBuilder();
        sb.append(HEADER);


        magnets.forEach(magnet -> {
            sb.append("<item>\n");
            sb.append("<title>");
            sb.append(magnet.getTitle());
            sb.append("</title>\n");

            sb.append("<link>");
            sb.append(magnet.getValue());
            sb.append("</link>\n");

            sb.append("<description></description><showrss:showid></showrss:showid><showrss:showname>");
            sb.append(magnet.getTitle());
            sb.append("</showrss:showname>\n");
            sb.append("</item>\n\n");
        });
        sb.append(TAIL);

        return sb.toString();
    }
}
