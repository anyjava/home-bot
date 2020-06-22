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
    private Long wrId;

    public Magnet toMagnet(ProgramType type) {
        return Magnet.init(SiteType.MAX, type, this.title, this.getWrId());
    }
}
