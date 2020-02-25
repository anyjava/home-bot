package dev.anyjava.bot.adapter.adapter;

import com.google.common.base.Strings;
import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import dev.anyjava.bot.torrent.domain.SiteType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Builder
@Getter
@ToString
public class MagnetDTO {
    private String title;
    private String url;

    public Magnet toMagnet(ProgramType type) {
        return Magnet.init(SiteType.MAX, type, this.title, this.getWrId());
    }

    public Long getWrId() {
        if (Strings.isNullOrEmpty(url)) {
            return 0L;
        }
        // for Wall
//        String s = url.split("/")[2];
//        String s1 = s.split("\\.")[0];

        // for Max
        String s1 = Arrays.stream(url.split("/"))
                .skip(3)
                .findFirst()
                .orElse("0");
        return Long.parseLong(s1);
    }
}
