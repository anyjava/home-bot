package dev.anyjava.bot.adapter.adapter;

import com.google.common.base.Strings;
import dev.anyjava.bot.torrent.domain.Magnet;
import dev.anyjava.bot.torrent.domain.ProgramType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MagnetDTO {
    private String title;
    private String url;

    public Magnet toMagnet(ProgramType type) {
        return Magnet.init(type, this.title, this.getWrId());
    }

    public Long getWrId() {
        if (Strings.isNullOrEmpty(url)) {
            return 0L;
        }
        String s = url.split("/")[2];
        String s1 = s.split("\\.")[0];
        return Long.parseLong(s1);
    }
}
